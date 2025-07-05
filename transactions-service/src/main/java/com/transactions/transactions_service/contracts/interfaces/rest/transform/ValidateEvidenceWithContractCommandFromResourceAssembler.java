package com.transactions.transactions_service.contracts.interfaces.rest.transform;

import com.transactions.transactions_service.contracts.domain.model.commands.ValidateEvidenceWithContractCommand;
import com.transactions.transactions_service.contracts.interfaces.rest.resources.ValidateEvidenceWithContractResource;

public class ValidateEvidenceWithContractCommandFromResourceAssembler {
    public static ValidateEvidenceWithContractCommand toCommandFromResource(
            ValidateEvidenceWithContractResource resource
    ) {
        return new ValidateEvidenceWithContractCommand(
                resource.evidenceType(),
                resource.employeeId(),
                resource.data()
        );
    }
}
