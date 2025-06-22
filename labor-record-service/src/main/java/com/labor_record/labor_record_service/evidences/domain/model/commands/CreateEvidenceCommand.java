package com.labor_record.labor_record_service.evidences.domain.model.commands;

public record CreateEvidenceCommand(
        Long employeeId,
        String type,
        String description,
        CreateCertificateCommand createCertificateCommand
) {
}
