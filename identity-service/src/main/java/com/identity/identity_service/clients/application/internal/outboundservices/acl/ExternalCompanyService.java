package com.identity.identity_service.clients.application.internal.outboundservices.acl;

import com.identity.identity_service.iam.domain.model.commands.CreateCompanyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalCompanyService {
    private final WebClient webClient;

    public ExternalCompanyService(
            WebClient.Builder builder,
            @Value("${clients.company.base-url}")
            String companyServiceUrl
    ) {
        this.webClient = builder.baseUrl(companyServiceUrl).build();
    }

    private String getJwtFromRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return "";
    }

    public Long createIfCompanyNotExists(CreateCompanyCommand command) {
        return webClient.post()
                .uri("/api/v1/companies/create-by-ruc")
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .bodyValue(command)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }
}
