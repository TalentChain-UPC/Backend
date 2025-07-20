package com.transactions.transactions_service.contracts.domain.services;

import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
import com.transactions.transactions_service.contracts.domain.model.commands.ValidateEvidenceWithContractCommand;

import java.util.Optional;

public interface ContractsCommandService {
    Optional<Contract> handle(CreateContractCommand command);
    void handle(ValidateEvidenceWithContractCommand command);
}
