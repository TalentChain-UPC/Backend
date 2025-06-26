package com.transactions.transactions_service.contracts.domain.model.commands;

public record CreateContractCommand(
        String name,
        String description,
        Long companyId,
        String evidenceType,
        String requirements,
        String startDateTime,
        String endDateTime
) {
}
