package com.labor_record.labor_record_service.evidences.domain.model.commands;

public record CreateEvidenceCommand(
        Long companyId,
        Long employeeId,
        String type,
        String description,
        String data,
        CreateCertificateCommand createCertificateCommand
) {
}
