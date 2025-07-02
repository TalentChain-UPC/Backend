package com.rewards.rewards_service.rewards.domain.model.entities;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rewards")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Rewards extends AuditableAbstractAggregateRoot<Rewards> {
    @Column(name = "catalog_id")
    private Integer Catalog_id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "type")
    private String type;

    public Rewards(CreateRewardCommand command) {
        this.Catalog_id = command.Catalog_id();
        this.name = command.name();
        this.price = command.price();
        this.description = command.description();
        this.image = command.image();
        this.type = command.type();
    }
}
