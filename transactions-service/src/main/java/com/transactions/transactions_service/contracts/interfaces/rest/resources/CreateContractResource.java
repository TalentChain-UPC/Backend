package com.transactions.transactions_service.contracts.interfaces.rest.resources;

public record CreateContractResource(
        String name,
        String description,
        Long companyId,
        String evidenceType,
        String requirements,
        String startDateTime,
        String endDateTime
) {
}
