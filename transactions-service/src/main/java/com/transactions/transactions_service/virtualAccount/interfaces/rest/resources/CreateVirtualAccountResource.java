package com.transactions.transactions_service.virtualAccount.interfaces.rest.resources;

public record CreateVirtualAccountResource(
        Long employeeId,
        String address
) {
}
