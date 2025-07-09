package com.transactions.transactions_service.contracts.infrastructure.persistence.jpa.repositories;

import com.transactions.transactions_service.contracts.domain.model.aggregates.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractsRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllBySmartContractAddress(String smartContractAddress);
}
