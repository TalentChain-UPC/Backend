package com.transactions.transactions_service.contracts.application.internal.commandServices;

import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.ExternalCompanyService;
import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.ContractStatus;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.EvidenceType;
import com.transactions.transactions_service.contracts.domain.services.ContractsCommandService;
import com.transactions.transactions_service.contracts.infrastructure.persistence.jpa.repositories.ContractsRepository;
import com.transactions.transactions_service.shared.utils.LocalDateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class ContractsCommandServiceImpl implements ContractsCommandService {
    private final ContractsRepository contractsRepository;
    private final ExternalCompanyService externalCompanyService;

    private final LocalDateTimeUtil localDateTimeUtil;

    public ContractsCommandServiceImpl(
            ContractsRepository contractsRepository,
            ExternalCompanyService externalCompanyService,
            LocalDateTimeUtil localDateTimeUtil) {
        this.contractsRepository = contractsRepository;
        this.externalCompanyService = externalCompanyService;
        this.localDateTimeUtil = localDateTimeUtil;
    }

    @Override
    public Optional<Contract> handle(CreateContractCommand command) {
        EvidenceType type;
        try{
            type=EvidenceType.valueOf(command.evidenceType());
        }catch (Exception e){
            return Optional.empty();
        }

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = localDateTimeUtil.parseFlexibleDateTime(command.startDateTime());
            endDateTime = localDateTimeUtil.parseFlexibleDateTime(command.endDateTime());
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }

        String status = localDateTimeUtil.calculateContractStatus(startDateTime,endDateTime);
        ContractStatus contractStatus;
        try{
            contractStatus=ContractStatus.valueOf(status);
        }catch (Exception e){
            return Optional.empty();
        }

        boolean existsCompany = externalCompanyService.verifyIfCompanyExists(command.companyId());
        if (!existsCompany) {
            return Optional.empty();
        }

        var contract = new Contract(command,type,contractStatus,startDateTime,endDateTime);
        contractsRepository.save(contract);
        return Optional.of(contract);
    }
}
