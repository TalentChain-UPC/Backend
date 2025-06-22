package com.labor_record.labor_record_service.evidences.interfaces.rest.transform;

import com.labor_record.labor_record_service.evidences.domain.model.commands.ValidateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.interfaces.rest.resources.ValidateEvidenceResource;

public class ValidateEvidenceCommandFromResourceAssembler {
    public static ValidateEvidenceCommand toCommandFromResource(ValidateEvidenceResource resource, Long evidenceId) {
        return new ValidateEvidenceCommand(
                evidenceId,
                resource.validate()
        );
    }
}
