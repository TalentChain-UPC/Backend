package com.transactions.transactions_service.contracts.interfaces.rest.resources;

public record ContractResource(
        Long id,
        String name,
        String description,
        Long companyId,
        String smartContractAddress,
        String evidenceType,
        String contractStatus,
        String startDateTime,
        String endDateTime
) {
}
