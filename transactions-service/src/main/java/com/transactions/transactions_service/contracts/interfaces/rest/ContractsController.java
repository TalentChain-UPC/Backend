package com.transactions.transactions_service.contracts.interfaces.rest;

import com.transactions.transactions_service.contracts.domain.services.ContractsCommandService;
import com.transactions.transactions_service.contracts.interfaces.rest.resources.ContractResource;
import com.transactions.transactions_service.contracts.interfaces.rest.resources.CreateContractResource;
import com.transactions.transactions_service.contracts.interfaces.rest.transform.ContractResourceFromEntityAssembler;
import com.transactions.transactions_service.contracts.interfaces.rest.transform.CreateContractCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractsController {
    private final ContractsCommandService contractsCommandService;

    public ContractsController(ContractsCommandService contractsCommandService) {
        this.contractsCommandService = contractsCommandService;
    }

    @PostMapping
    public ResponseEntity<ContractResource> createContract(@RequestBody CreateContractResource createContractResource){
        var command = CreateContractCommandFromResourceAssembler
                .toCommandFromResource(createContractResource);
        var contract = contractsCommandService.handle(command);
        if(contract.isEmpty())return ResponseEntity.badRequest().build();
        var resource = ContractResourceFromEntityAssembler
                .toResourceFromEntity(contract.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

}
