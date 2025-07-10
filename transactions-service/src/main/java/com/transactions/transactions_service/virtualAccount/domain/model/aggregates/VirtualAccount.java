package com.transactions.transactions_service.virtualAccount.domain.model.aggregates;

import com.transactions.transactions_service.virtualAccount.domain.model.commands.CreateVirtualAccountCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VirtualAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String address;
    private Integer balance;
    public VirtualAccount() {}
    public VirtualAccount(CreateVirtualAccountCommand command) {
        this.employeeId= command.employeeId();
        this.address = command.address();
        this.balance=0;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getAddress() {
        return address;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
