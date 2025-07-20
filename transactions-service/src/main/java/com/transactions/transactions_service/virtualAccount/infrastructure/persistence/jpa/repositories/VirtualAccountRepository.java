package com.transactions.transactions_service.virtualAccount.infrastructure.persistence.jpa.repositories;

import com.transactions.transactions_service.virtualAccount.domain.model.aggregates.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, Long> {
    Optional<VirtualAccount> findByEmployeeId(Long employeeId);
    boolean existsByAddress(String address);
}
