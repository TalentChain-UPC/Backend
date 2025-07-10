package com.transactions.transactions_service.virtualAccount.interfaces.rest.transform;

import com.transactions.transactions_service.virtualAccount.domain.model.commands.CreateVirtualAccountCommand;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.resources.CreateVirtualAccountResource;

public class CreateVirtualAccountCommandFromResourceAssembler {
    public static CreateVirtualAccountCommand toCommandFromResource(CreateVirtualAccountResource resource) {
        return new CreateVirtualAccountCommand(resource.employeeId(), resource.address());
    }
}
