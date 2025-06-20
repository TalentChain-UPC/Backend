package com.labor_record.labor_record_service.evidences.domain.model.commands;

public record CreateCertificateCommand(
        Long employeeId,
        String url
) {
}
