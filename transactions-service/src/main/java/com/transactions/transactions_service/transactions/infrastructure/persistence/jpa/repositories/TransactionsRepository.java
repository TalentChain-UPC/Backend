package com.transactions.transactions_service.transactions.infrastructure.persistence.jpa.repositories;

import com.transactions.transactions_service.transactions.domain.model.aggregates.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
