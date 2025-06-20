package com.company.company_service.companies.domain.model.entities;

import com.company.company_service.companies.domain.model.commands.CreateCompanyCommand;
import com.company.company_service.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Companies extends AuditableAbstractAggregateRoot<Companies> {
    @Column(name = "name")
    private String name;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "sector")
    private String sector;

    public Companies(CreateCompanyCommand command) {
        this.name = command.name();
        this.ruc = command.ruc();
        this.sector = command.sector();
    }
}