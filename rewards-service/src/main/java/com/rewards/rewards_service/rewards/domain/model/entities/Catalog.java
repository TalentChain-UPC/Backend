package com.rewards.rewards_service.rewards.domain.model.entities;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateCatalogCommand;
import com.rewards.rewards_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog extends AuditableAbstractAggregateRoot<Catalog> {
    @Column(name = "Companies_id")
    private Long Companies_id;
    public Catalog() {}
    public Catalog(CreateCatalogCommand command) {
        this.Companies_id = command.Companies_id();
    }

    public Long getCompanies_id() {
        return Companies_id;
    }
}
