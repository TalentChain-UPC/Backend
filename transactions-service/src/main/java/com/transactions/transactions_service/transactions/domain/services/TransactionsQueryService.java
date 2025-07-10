package com.transactions.transactions_service.transactions.domain.services;

import com.transactions.transactions_service.transactions.domain.model.aggregates.Transaction;
import com.transactions.transactions_service.transactions.domain.model.queries.GetAllTransactionsByEmployeeIdQuery;

import java.util.List;

public interface TransactionsQueryService {
    List<Transaction> handle(GetAllTransactionsByEmployeeIdQuery query);
}
