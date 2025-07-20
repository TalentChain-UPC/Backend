package com.transactions.transactions_service.contracts.interfaces.rest.transform;

import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
import com.transactions.transactions_service.contracts.interfaces.rest.resources.CreateContractResource;

public class CreateContractCommandFromResourceAssembler {
    public static CreateContractCommand toCommandFromResource(CreateContractResource resource){
        return new CreateContractCommand(
                resource.name(),
                resource.description(),
                resource.companyId(),
                resource.evidenceType(),
                resource.requirements(),
                resource.startDateTime(),
                resource.endDateTime()
        );
    }
}
