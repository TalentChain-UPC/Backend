package com.transactions.transactions_service.contracts.application.internal.commandServices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.ExternalCompanyService;
import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.ExternalTransactionsContractsService;
import com.transactions.transactions_service.contracts.application.internal.outboundservices.acl.Web3Utils;
import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
//import com.transactions.transactions_service.contracts.domain.model.valueObjects.ContractStatus;
import com.transactions.transactions_service.contracts.domain.model.commands.ValidateEvidenceWithContractCommand;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.EvidenceType;
import com.transactions.transactions_service.contracts.domain.services.ContractsCommandService;
import com.transactions.transactions_service.contracts.infrastructure.persistence.jpa.repositories.ContractsRepository;
import com.transactions.transactions_service.shared.utils.EvidenceBonusUtil;
import com.transactions.transactions_service.shared.utils.LocalDateTimeUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
class ContractsCommandServiceImpl implements ContractsCommandService {
    private final ContractsRepository contractsRepository;
    private final ExternalCompanyService externalCompanyService;
    private final ExternalTransactionsContractsService externalTransactionsService;

    private final Web3Utils web3Utils;
    private final LocalDateTimeUtil localDateTimeUtil;
    private final EvidenceBonusUtil evidenceBonusUtil;

    public ContractsCommandServiceImpl(
            ContractsRepository contractsRepository,
            ExternalCompanyService externalCompanyService,
            ExternalTransactionsContractsService externalTransactionsService,
            Web3Utils web3Utils,
            LocalDateTimeUtil localDateTimeUtil,
            EvidenceBonusUtil evidenceBonusUtil) {
        this.contractsRepository = contractsRepository;
        this.externalCompanyService = externalCompanyService;
        this.externalTransactionsService = externalTransactionsService;
        this.web3Utils = web3Utils;
        this.localDateTimeUtil = localDateTimeUtil;
        this.evidenceBonusUtil = evidenceBonusUtil;
    }

    private String getContractByEvidenceType(EvidenceType evidenceType) {
        return switch (evidenceType){
            //case CERTIFICATE -> "0x2943b2d3257235d808f25b69a41bda6450bd863b";
            case CERTIFICATE -> "";
            //case PROMOTION -> "0xf55df20dc6369ab9799335412a98aa4918e7192b";
            case PROMOTION -> "";
            //case PUNCTUALITY -> "0x22b047fc51bebcc0223f53ae82ceb4df029a32de";
            case PUNCTUALITY -> "";
            //case SPECIALIZATION -> "0x66f394759474a56fb1171cb9cd4cfa6ee6616826";
            case SPECIALIZATION -> "";
            //case STUDY_WORKSHOP -> "0x56b9cefbc4ab053a369403b637c91a3e56c3b269";
            case STUDY_WORKSHOP -> "";
            //case JOB_TRAINING -> "0xc917402d3b1ceb60b6bf216eb19ab774194416c7";
            case JOB_TRAINING -> "";
            //case PROACTIVITY -> "0x113feaab0c7f7df70bbf51a69b29fb49ab704602";
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

        boolean existsCompany = externalCompanyService.verifyIfCompanyExists(command.companyId());
        if (!existsCompany) {
            return Optional.empty();
        }

        String smartContractAddress = getContractByEvidenceType(type);

        var contract = new Contract(command,smartContractAddress,type,startDateTime,endDateTime);
        contractsRepository.save(contract);
        return Optional.of(contract);
    }

    @Override
    public void handle(ValidateEvidenceWithContractCommand command) {
        EvidenceType type;
        try{
            type=EvidenceType.valueOf(command.evidenceType());
        }catch (Exception e){
            return;
        }
        String smartContractAddress = getContractByEvidenceType(type);

        Integer virtualCoins = 0;

        //se le transfiere la cantidad por defecto del smart contract
        //BigInteger reward = web3Utils.getReward(smartContractAddress);
        BigInteger reward = new BigInteger("100");
        int value=0;
        if(reward.compareTo(BigInteger.valueOf(Integer.MIN_VALUE))>=0 &&
           reward.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))<=0){
            value = reward.intValue();
        }else {
            return;
        }

        virtualCoins = virtualCoins + value;

        // monedas adicionales
        //validate commnad.data() and contract segun tipo de evidenci
        var contracts = contractsRepository.findAllBySmartContractAddress(smartContractAddress);


        //obtener json de data y obtener json de contract.get.requirements
        int additionalCoins = contracts.stream()
                .filter(contract -> {
                    LocalDateTime now = LocalDateTime.now();
                    return !now.isBefore(contract.getStartDateTime()) && !now.isAfter(contract.getEndDateTime());
                })
                .map(contract -> {
                    try {
                        return evidenceBonusUtil.calculateBonus(
                                contract.getType(),
                                contract.getRequirements(),
                                command.data()
                        );
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .reduce(0, Integer::sum);
                /*.map(contract -> {
                    int bonusValue = 0;
                    try {
                        String requirements = contract.getRequirements();
                        String data = command.data();

                        // bonusValue = bonusValue + evidenceBonusUtil(contract.getType(), requirements, data);

                        JsonNode requirementsJson = objectMapper.readTree(requirements);
                        JsonNode dataJson = objectMapper.readTree(data);

                        JsonNode bonusRules = requirementsJson.get("bonificacionesPorHoras");
                        int hoursNumber = dataJson.get("numeroDeHoras").asInt();


                        for(JsonNode rule : bonusRules){
                            int min = rule.get("min").asInt();
                            int max = rule.get("max").asInt();
                            if(hoursNumber>= min && hoursNumber<= max){
                                bonusValue = rule.get("recompensa").asInt();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        return bonusValue;
                    }
                    return bonusValue;
                })*/
                //.reduce(0, Integer::sum);

        virtualCoins = virtualCoins + additionalCoins;


        /*String trxHash = web3Utils.executeContract(
                smartContractAddress,
                command.companyId().toString(),
                command.employeeId().toString()
        );*/
        String trxHash = "EC285HEBXN3I28C3NCNC3221032968OAMZF";

        // externalTransactionsService.transfer(command.employeeId(),command.evidenceType(),monedasVirtuales);
        externalTransactionsService.executeTransfer(
                command.companyId(),
                command.employeeId(),
                command.evidenceType(),
                virtualCoins,
                LocalDateTime.now().toString(),
                command.fullName(),
                trxHash
        );
    }
}
