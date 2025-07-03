package com.rewards.rewards_service.rewards.domain.model.entities;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateCatalogCommand;
import com.rewards.rewards_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalog")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Catalog extends AuditableAbstractAggregateRoot<Catalog> {
    @Column(name = "Companies_id")
    private Long Companies_id;

    public Catalog(CreateCatalogCommand command) {
        this.Companies_id = command.Companies_id();
    }
}
