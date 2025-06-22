package com.labor_record.labor_record_service.evidences.interfaces.rest.transform;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.CertificateResource;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.EvidenceResource;

public class EvidenceResourceFromEntityAssembler {
    public static EvidenceResource toResourceFromEntity(Evidence entity){
        CertificateResource certificateResource;
        if(entity.getCertificate()==null){
            certificateResource=new CertificateResource(0L,"");
        }else{
            certificateResource=new CertificateResource(
                    entity.getCertificate().getId(),
                    entity.getCertificate().getUrl());
        }

        return new EvidenceResource(
                entity.getId(),
                entity.getEmployeeId(),
                entity.getType().name(),
                entity.getDescription(),
                entity.isValidated(),
                entity.isRequireCertification(),
                certificateResource
        );

    }
}
