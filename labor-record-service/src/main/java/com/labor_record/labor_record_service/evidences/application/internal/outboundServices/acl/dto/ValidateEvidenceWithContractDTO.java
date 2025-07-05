package com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl.dto;

public record ValidateEvidenceWithContractDTO(
        String evidenceType,
        Long employeeId,
        String data
) {
}
