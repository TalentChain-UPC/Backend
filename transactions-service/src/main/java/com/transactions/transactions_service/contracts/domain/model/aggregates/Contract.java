package com.transactions.transactions_service.contracts.domain.model.aggregates;

import com.transactions.transactions_service.contracts.domain.model.commands.CreateContractCommand;
//import com.transactions.transactions_service.contracts.domain.model.valueObjects.ContractStatus;
import com.transactions.transactions_service.contracts.domain.model.valueObjects.EvidenceType;
import com.transactions.transactions_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract extends AuditableAbstractAggregateRoot<Contract> {
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Long companyId;
    private String smartContractAddress;

    @Enumerated(EnumType.STRING)
    private EvidenceType type;

    @Column(columnDefinition = "json")
    private String requirements;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Contract() {
    }

    public Contract(
            CreateContractCommand command,
            String smartContractAddress,
            EvidenceType type,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        this.name=command.name();
        this.description=command.description();
        this.companyId=command.companyId();
        this.smartContractAddress=smartContractAddress;
        this.type=type;
        this.requirements=command.requirements();
        this.startDateTime=startDateTime;
        this.endDateTime=endDateTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getSmartContractAddress() {
        return smartContractAddress;
    }

    public EvidenceType getType() {
        return type;
    }

    public String getRequirements() {
        return requirements;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
