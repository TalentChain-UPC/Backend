package com.transactions.transactions_service.virtualAccount.application.internal.queryServices;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import com.transactions.transactions_service.virtualAccount.domain.model.queries.GetVirtualAccountByEmployeeIdQuery;
import com.transactions.transactions_service.virtualAccount.domain.services.VirtualAccountQueryService;
import com.transactions.transactions_service.virtualAccount.infrastructure.persistence.jpa.repositories.VirtualAccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VirtualAccountQueryServiceImpl implements VirtualAccountQueryService {
    private final VirtualAccountRepository virtualAccountRepository;

    public VirtualAccountQueryServiceImpl(VirtualAccountRepository virtualAccountRepository) {
        this.virtualAccountRepository = virtualAccountRepository;
    }

    @Override
    public Optional<VirtualAccount> handle(GetVirtualAccountByEmployeeIdQuery query) {
        return virtualAccountRepository.findByEmployeeId(query.employeeId());
    }
}
