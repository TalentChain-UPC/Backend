package com.transactions.transactions_service.transactions.interfaces.rest.resources;

public record TransactionResource(
        String transactionId,
        Long companyId,
        Long employeeId,
        String description,
        Integer amount,
        String evidenceType,
        String timestamp
) {
}
