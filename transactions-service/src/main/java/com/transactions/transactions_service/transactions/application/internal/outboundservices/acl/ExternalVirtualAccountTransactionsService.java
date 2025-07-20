package com.transactions.transactions_service.transactions.application.internal.outboundservices.acl;

import com.transactions.transactions_service.virtualAccount.interfaces.acl.VirtualAccountContextFacade;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExternalVirtualAccountTransactionsService {
    private final VirtualAccountContextFacade virtualAccountContextFacade;

    public ExternalVirtualAccountTransactionsService(VirtualAccountContextFacade virtualAccountContextFacade) {
        this.virtualAccountContextFacade = virtualAccountContextFacade;
    }
    @Async
    public void updateVirtualAccountBalance(Long employeeId, Integer newBalance) {
        virtualAccountContextFacade.updateBalance(employeeId,newBalance);
    }
}
