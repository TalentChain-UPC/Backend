package com.transactions.transactions_service.contracts.interfaces.rest.transform;

import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import com.transactions.transactions_service.contracts.interfaces.rest.resources.ContractResource;

public class ContractResourceFromEntityAssembler {
    public static ContractResource toResourceFromEntity(Contract entity){
        return new ContractResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCompanyId(),
                entity.getSmartContractAddress(),
                entity.getType().name(),
                entity.getStatus().name(),
                entity.getStartDateTime().toString(),
                entity.getEndDateTime().toString()
        );
    }
}
