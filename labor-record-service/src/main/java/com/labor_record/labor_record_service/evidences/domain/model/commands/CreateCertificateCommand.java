package com.labor_record.labor_record_service.evidences.domain.model.commands;

public record CreateCertificateCommand(
        String url,
        String name,
        String institutionName,
        String issuedDate
) {
}
