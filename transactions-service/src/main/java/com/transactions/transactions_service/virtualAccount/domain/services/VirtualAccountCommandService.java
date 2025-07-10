package com.transactions.transactions_service.virtualAccount.domain.services;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import com.transactions.transactions_service.virtualAccount.domain.model.commands.CreateVirtualAccountCommand;

import java.util.Optional;

public interface VirtualAccountCommandService {
    Optional<VirtualAccount> handle(CreateVirtualAccountCommand command);
}
