package com.transactions.transactions_service.contracts.application.internal.outboundservices.acl;

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
            String companyServiceUrl) {
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

    public Boolean verifyIfCompanyExists(Long companyId) {
        return webClient.get()
                .uri("/api/v1/companies/{id}/exists",companyId)
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
