package com.transactions.transactions_service.virtualAccount.domain.model.commands;

public record UpdateBalanceCommand(
        Long employeeId,
        Integer newBalance
) {
}
