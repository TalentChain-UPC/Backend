package com.transactions.transactions_service.transactions.application.internal.queryServices;

import com.transactions.transactions_service.transactions.domain.model.aggregates.Transaction;
import com.transactions.transactions_service.transactions.domain.model.queries.GetAllTransactionsByEmployeeIdQuery;
import com.transactions.transactions_service.transactions.domain.services.TransactionsQueryService;
import com.transactions.transactions_service.transactions.infrastructure.persistence.jpa.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsQueryServiceImpl implements TransactionsQueryService {
    private final TransactionsRepository transactionsRepository;

    public TransactionsQueryServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public List<Transaction> handle(GetAllTransactionsByEmployeeIdQuery query) {
        return transactionsRepository.findAllByEmployeeId(query.employeeId());
    }
}
