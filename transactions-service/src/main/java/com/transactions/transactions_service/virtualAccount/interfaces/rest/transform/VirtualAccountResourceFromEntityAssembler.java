package com.transactions.transactions_service.virtualAccount.interfaces.rest.transform;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.resources.VirtualAccountResource;

public class VirtualAccountResourceFromEntityAssembler {
    public static VirtualAccountResource toResourceFromEntity(VirtualAccount entity) {
        return new VirtualAccountResource(
                entity.getId(),
                entity.getEmployeeId(),
                entity.getAddress(),
                entity.getBalance()
        );
    }
}
