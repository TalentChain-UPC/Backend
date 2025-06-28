package com.transactions.transactions_service.contracts.application.internal.commandServices;

import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.ExternalCompanyService;
import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.Web3Utils;
import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
//import com.transactions.transactions_service.contracts.domain.model.valueObjects.ContractStatus;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.EvidenceType;
import com.transactions.transactions_service.contracts.domain.services.ContractsCommandService;
import com.transactions.transactions_service.contracts.infrastructure.persistence.jpa.repositories.ContractsRepository;
import com.transactions.transactions_service.shared.utils.LocalDateTimeUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
class ContractsCommandServiceImpl implements ContractsCommandService {
    private final ContractsRepository contractsRepository;
    private final ExternalCompanyService externalCompanyService;

    private final Web3Utils web3Utils;
    private final LocalDateTimeUtil localDateTimeUtil;

    public ContractsCommandServiceImpl(
            ContractsRepository contractsRepository,
            ExternalCompanyService externalCompanyService,
            Web3Utils web3Utils,
            LocalDateTimeUtil localDateTimeUtil) {
        this.contractsRepository = contractsRepository;
        this.externalCompanyService = externalCompanyService;
        this.web3Utils = web3Utils;
        this.localDateTimeUtil = localDateTimeUtil;
    }

    private String getContractByEvidenceType(EvidenceType evidenceType) {
        return switch (evidenceType){
            case CERTIFICATE -> "";
            case PROMOTION -> "";
            case PUNCTUALITY -> "";
            case SPECIALIZATION -> "";
            case STUDY_WORKSHOP -> "";
            case JOB_TRAINING -> "";
            case PROACTIVITY -> "";
            default -> "";
        };
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

        /*String status = localDateTimeUtil.calculateContractStatus(startDateTime,endDateTime);
        ContractStatus contractStatus;
        try{
            contractStatus=ContractStatus.valueOf(status);
        }catch (Exception e){
            return Optional.empty();
        }*/

        boolean existsCompany = externalCompanyService.verifyIfCompanyExists(command.companyId());
        if (!existsCompany) {
            return Optional.empty();
        }

        String smartContractAddress = getContractByEvidenceType(type);
        //BigInteger reward = web3Utils.getReward(smartContractAddress);

        var contract = new Contract(command,smartContractAddress,type,startDateTime,endDateTime);
        contractsRepository.save(contract);
        return Optional.of(contract);
    }
}
