package com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalTransactionsService {
    private final WebClient webClient;
    public ExternalTransactionsService(
            WebClient.Builder builder,
            @Value("${clients.transactions.base-url}")
            String trxServiceUrl) {
        this.webClient = builder.baseUrl(trxServiceUrl).build();
    }
    private String getJwtFromRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return "";
    }
    public void validateContract(Long employeeId) {
        webClient.get()
                .uri("/api/v1/contracts/{id}/exists",employeeId)
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
