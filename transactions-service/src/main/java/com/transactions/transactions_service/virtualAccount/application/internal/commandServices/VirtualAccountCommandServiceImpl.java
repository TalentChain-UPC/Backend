package com.transactions.transactions_service.virtualAccount.application.internal.commandServices;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import com.transactions.transactions_service.virtualAccount.domain.model.commands.CreateVirtualAccountCommand;
import com.transactions.transactions_service.virtualAccount.domain.model.commands.UpdateBalanceCommand;
import com.transactions.transactions_service.virtualAccount.domain.services.VirtualAccountCommandService;
import com.transactions.transactions_service.virtualAccount.infrastructure.persistence.jpa.repositories.VirtualAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VirtualAccountCommandServiceImpl implements VirtualAccountCommandService {
    private final VirtualAccountRepository virtualAccountRepository;

    public VirtualAccountCommandServiceImpl(VirtualAccountRepository virtualAccountRepository) {
        this.virtualAccountRepository = virtualAccountRepository;
    }

    @Override
    public Optional<VirtualAccount> handle(CreateVirtualAccountCommand command) {
        if (virtualAccountRepository.existsByAddress(command.address())){
            return Optional.empty();
        }
        var virtualAccount = new VirtualAccount(command);
        virtualAccountRepository.save(virtualAccount);
        return Optional.of(virtualAccount);
    }

    @Override
    public void handle(UpdateBalanceCommand command) {
        var virtualAccount = virtualAccountRepository.findByEmployeeId(command.employeeId());
        if (virtualAccount.isEmpty())return;
        int prevBalance = virtualAccount.get().getBalance();
        int newBalance = prevBalance+command.newBalance();
        virtualAccount.get().setBalance(newBalance);
        virtualAccountRepository.save(virtualAccount.get());
    }
}
