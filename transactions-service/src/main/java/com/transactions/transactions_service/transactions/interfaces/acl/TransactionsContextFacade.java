package com.transactions.transactions_service.transactions.interfaces.acl;

import com.transactions.transactions_service.transactions.domain.model.commands.CreateTransactionCommand;
import com.transactions.transactions_service.transactions.domain.services.TransactionsCommandService;
import org.springframework.stereotype.Service;

@Service
public class TransactionsContextFacade {
    private final TransactionsCommandService transactionsCommandService;

    public TransactionsContextFacade(TransactionsCommandService transactionsCommandService) {
        this.transactionsCommandService = transactionsCommandService;
    }

    public void createTransaction(
            Long companyId,
            Long employeeId,
            String evidenceType,
            Integer virtualCoins,
            String timestamp
    ){
        transactionsCommandService.handle(
                new CreateTransactionCommand(
                        companyId,
                        employeeId,
                        evidenceType,
                        virtualCoins,
                        timestamp
                )
        );
    }
}
