package com.transactions.transactions_service.virtualAccount.domain.services;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import com.transactions.transactions_service.virtualAccount.domain.model.queries.GetVirtualAccountByEmployeeIdQuery;

import java.util.Optional;

public interface VirtualAccountQueryService {
    Optional<VirtualAccount> handle(GetVirtualAccountByEmployeeIdQuery query);
}
