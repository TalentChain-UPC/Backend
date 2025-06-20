package com.labor_record.labor_record_service.evidences.interfaces.rest.transform;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.CertificateResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.EvidenceResource;

public class EvidenceResourceFromEntityAssembler {
    public static EvidenceResource toResourceFromEntity(Evidence entity){
        return new EvidenceResource(
                entity.getId(),
                entity.getType().name(),
                entity.getDescription(),
                entity.isValidated(),
                entity.isRequireCertification(),
                new CertificateResource(
                        entity.getId(),
                        entity.getCertificate().getEmployeeId(),
                        entity.getCertificate().getUrl())
        );

    }
}
