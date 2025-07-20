package com.identity.identity_service.clients.application.internal.outboundservices.acl;

import com.identity.identity_service.clients.application.internal.outboundservices.acl.dto.CreateVirtualAccountDTO;
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
            String trxsService
    ) {
        this.webClient = builder.baseUrl(trxsService).build();
    }

    private String getJwtFromRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("Authorization");
        }
        return "";
    }

    public void createEmployeeVirtualAccount(Long employeeId, String address) {
        CreateVirtualAccountDTO dto = new CreateVirtualAccountDTO(employeeId, address);
        webClient.post()
                .uri("/api/v1/virtual-accounts")
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Long.class)
                .subscribe();
    }
}
