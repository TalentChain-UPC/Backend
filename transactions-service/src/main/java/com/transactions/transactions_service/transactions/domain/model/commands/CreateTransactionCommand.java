package com.transactions.transactions_service.transactions.domain.model.commands;

public record CreateTransactionCommand(
        Long companyId,
        Long employeeId,
        String evidenceType,
        Integer virtualCoins,
        String timestamp
) {
}
