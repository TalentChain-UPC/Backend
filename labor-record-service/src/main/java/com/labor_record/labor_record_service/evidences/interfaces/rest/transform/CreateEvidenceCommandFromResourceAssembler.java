package com.labor_record.labor_record_service.evidences.interfaces.rest.transform;

import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateCertificateCommand;
import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.CreateEvidenceResource;

public class CreateEvidenceCommandFromResourceAssembler {
    public static CreateEvidenceCommand toCommandFromResource(CreateEvidenceResource resource) {
        return new CreateEvidenceCommand(
                resource.employeeId(),
                resource.type(),
                resource.description(),
                new CreateCertificateCommand(
                        resource.url()
                )
        );

    }
}
