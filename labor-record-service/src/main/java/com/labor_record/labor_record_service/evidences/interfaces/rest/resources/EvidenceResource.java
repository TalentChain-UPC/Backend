package com.labor_record.labor_record_service.evidences.interfaces.rest.resources;

public record EvidenceResource(
        Long id,
        String type,
        String description,
        boolean validated,
        boolean requiresCertification,
        CertificateResource certificateResource
) {
}
