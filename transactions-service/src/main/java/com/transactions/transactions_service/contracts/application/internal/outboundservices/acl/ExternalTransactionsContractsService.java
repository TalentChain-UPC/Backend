package com.transactions.transactions_service.contracts.application.internal.outboundservices.acl;

import com.transactions.transactions_service.transactions.interfaces.acl.TransactionsContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalTransactionsContractsService {
    private final TransactionsContextFacade transactionsContextFacade;

    public ExternalTransactionsContractsService(TransactionsContextFacade transactionsContextFacade) {
        this.transactionsContextFacade = transactionsContextFacade;
    }

    public void executeTransfer(
            Long companyId,
            Long employeeId,
            String evidenceType,
            Integer virtualCoins,
            String timestamp
    ){
        transactionsContextFacade.createTransaction(
                companyId,
                employeeId,
                evidenceType,
                virtualCoins,
                timestamp
        );
    }
}
