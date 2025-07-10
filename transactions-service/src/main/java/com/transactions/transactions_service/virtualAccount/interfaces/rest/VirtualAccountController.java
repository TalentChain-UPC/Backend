package com.transactions.transactions_service.virtualAccount.interfaces.rest;

import com.transactions.transactions_service.virtualAccount.domain.model.queries.GetVirtualAccountByEmployeeIdQuery;
import com.transactions.transactions_service.virtualAccount.domain.services.VirtualAccountCommandService;
import com.transactions.transactions_service.virtualAccount.domain.services.VirtualAccountQueryService;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.resources.CreateVirtualAccountResource;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.resources.VirtualAccountResource;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.transform.CreateVirtualAccountCommandFromResourceAssembler;
import com.transactions.transactions_service.virtualAccount.interfaces.rest.transform.VirtualAccountResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/virtual-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class VirtualAccountController {
    private final VirtualAccountCommandService virtualAccountCommandService;
    private final VirtualAccountQueryService virtualAccountQueryService;

    public VirtualAccountController(
            VirtualAccountCommandService virtualAccountCommandService,
            VirtualAccountQueryService virtualAccountQueryService) {
        this.virtualAccountCommandService = virtualAccountCommandService;
        this.virtualAccountQueryService = virtualAccountQueryService;
    }

    @PostMapping
    public ResponseEntity<VirtualAccountResource> createEmployeeVirtualAccount(
            @RequestBody CreateVirtualAccountResource createVirtualAccountResource){
        var command = CreateVirtualAccountCommandFromResourceAssembler.toCommandFromResource(createVirtualAccountResource);
        var virtualAccount = virtualAccountCommandService.handle(command);
        if (virtualAccount.isEmpty())return ResponseEntity.badRequest().build();
        var resource = VirtualAccountResourceFromEntityAssembler.toResourceFromEntity(virtualAccount.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<VirtualAccountResource> getEmployeeVirtualAccount(@PathVariable Long id){
        var virtualAccount = virtualAccountQueryService.handle(new GetVirtualAccountByEmployeeIdQuery(id));
        if (virtualAccount.isEmpty())return ResponseEntity.badRequest().build();
        var resource = VirtualAccountResourceFromEntityAssembler.toResourceFromEntity(virtualAccount.get());
        return ResponseEntity.ok(resource);
    }
}
