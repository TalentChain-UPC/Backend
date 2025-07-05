package com.transactions.transactions_service.transactions.domain.services;

import com.transactions.transactions_service.transactions.domain.model.commands.CreateTransactionCommand;

public interface TransactionsCommandService {
    void handle(CreateTransactionCommand command);
}
