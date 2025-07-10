package com.transactions.transactions_service.virtualAccount.interfaces.acl;

import com.transactions.transactions_service.virtualAccount.domain.model.commands.UpdateBalanceCommand;
import com.transactions.transactions_service.virtualAccount.domain.services.VirtualAccountCommandService;
import org.springframework.stereotype.Service;

@Service
public class VirtualAccountContextFacade {
    private final VirtualAccountCommandService virtualAccountCommandService;

    public VirtualAccountContextFacade(VirtualAccountCommandService virtualAccountCommandService) {
        this.virtualAccountCommandService = virtualAccountCommandService;
    }

    public void updateBalance(Long employeeId, Integer amount) {
        virtualAccountCommandService.handle(new UpdateBalanceCommand(employeeId, amount));
    }
}
