package com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalProfileService {
    private final WebClient webClient;

    public ExternalProfileService(
            WebClient.Builder builder,
            @Value("${clients.identity.base-url}")
            String identityServiceUrl) {
        this.webClient = builder.baseUrl(identityServiceUrl).build();
    }

    private String getJwtFromRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return "";
    }

    public Boolean verifyIfEmployeeExists(Long employeeId) {
        return webClient.get()
                .uri("/api/v1/employees/{id}/exists",employeeId)
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
