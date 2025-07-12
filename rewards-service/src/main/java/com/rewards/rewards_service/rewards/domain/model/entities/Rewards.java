package com.rewards.rewards_service.rewards.domain.model.entities;

import com.rewards.rewards_service.rewards.domain.model.commands.CreateRewardCommand;
import com.rewards.rewards_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rewards")
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

    public Rewards() {}

    public Integer getCatalog_id() {
        return Catalog_id;
    }

    public void setCatalog_id(Integer catalog_id) {
        Catalog_id = catalog_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
