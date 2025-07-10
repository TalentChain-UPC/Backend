package com.transactions.transactions_service.transactions.interfaces.rest.transform;

import com.transactions.transactions_service.transactions.domain.model.aggregates.Transaction;
import com.transactions.transactions_service.transactions.interfaces.rest.resources.TransactionResource;

public class TransactionResourceFromEntityAssembler {
    public static TransactionResource toResourceFromEntity(Transaction entity) {
        return new TransactionResource(
                entity.getTransactionId(),
                entity.getCompanyId(),
                entity.getEmployeeId(),
                entity.getDescription(),
                entity.getAmount(),
                entity.getEvidenceType(),
                entity.getTimestamp()
        );
    }
}
