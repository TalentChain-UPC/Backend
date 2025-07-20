package com.company.company_service.companies.application.internal.outboundservices.acl;

import com.company.company_service.companies.application.internal.outboundservices.acl.dto.CreateCatalogDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalRewardsCompanyService {
    private final WebClient webClient;

    public ExternalRewardsCompanyService(
            WebClient.Builder builder,
            @Value("${clients.rewards.base-url}")
            String rewardsServiceUrl
    ) {
        this.webClient = builder.baseUrl(rewardsServiceUrl).build();
    }

    private String getJwtFromRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return "";
    }

    public void createCatalog(CreateCatalogDTO command) {
        webClient.post()
                .uri("/api/v1/catalog")
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .bodyValue(command)
                .retrieve()
                .bodyToMono(Long.class)
                .subscribe();
    }
}
