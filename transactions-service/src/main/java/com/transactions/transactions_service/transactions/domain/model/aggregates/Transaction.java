package com.transactions.transactions_service.transactions.domain.model.aggregates;

import com.transactions.transactions_service.transactions.domain.model.commands.CreateTransactionCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private Long companyId;
    private Long employeeId;
    private String description;
    private Integer amount;
    private String evidenceType;
    private String timestamp;

    public Transaction(CreateTransactionCommand command,String description) {
        this.transactionId=command.trxHash();
        this.companyId=command.companyId();
        this.employeeId=command.employeeId();
        this.description=description;
        this.amount=command.virtualCoins();
        this.evidenceType=command.evidenceType();
        this.timestamp=command.timestamp();
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
