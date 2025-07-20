package com.transactions.transactions_service.transactions.interfaces.rest;

import com.transactions.transactions_service.transactions.domain.model.queries.GetAllTransactionsByEmployeeIdQuery;
import com.transactions.transactions_service.transactions.domain.services.TransactionsQueryService;
import com.transactions.transactions_service.transactions.interfaces.rest.resources.TransactionResource;
import com.transactions.transactions_service.transactions.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/transactions",produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsController {
    private final TransactionsQueryService transactionsQueryService;

    public TransactionsController(TransactionsQueryService transactionsQueryService) {
        this.transactionsQueryService = transactionsQueryService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TransactionResource>> getTransactionsByEmployeeId(
            @PathVariable Long employeeId) {
        var transactions = transactionsQueryService
                .handle(new GetAllTransactionsByEmployeeIdQuery(employeeId));
        var resources = transactions.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
