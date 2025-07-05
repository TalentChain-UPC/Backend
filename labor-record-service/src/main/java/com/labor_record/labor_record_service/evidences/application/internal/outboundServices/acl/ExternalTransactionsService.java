package com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl;

import com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl.dto.ValidateEvidenceWithContractDTO;
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
    public Boolean validateEvidenceWithContract(String evidenceType, Long employeeId, String data) {
        var dto = new ValidateEvidenceWithContractDTO(evidenceType, employeeId, data);
        return webClient.post()
                .uri("/api/v1/contracts/validate-evidence")
                .header(HttpHeaders.AUTHORIZATION, getJwtFromRequest())
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
