package com.transactions.transactions_service.virtualAccount.domain.model.commands;

public record CreateVirtualAccountCommand(
        Long employeeId,
        String address
) {
}
