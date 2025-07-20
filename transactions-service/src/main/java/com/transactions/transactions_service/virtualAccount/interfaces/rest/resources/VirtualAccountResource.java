package com.transactions.transactions_service.virtualAccount.interfaces.rest.resources;

public record VirtualAccountResource(
        Long id,
        Long employeeId,
        String address,
        Integer balance
) {
}
