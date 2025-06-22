package com.company.company_service.companies.domain.model.entities;

import com.company.company_service.companies.domain.model.commands.CreatePlanCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_per_employ")
    private Double price_per_employ;

    @Column(name = "max_employees")
    private Integer max_employees;

    @Column(name = "description")
    private String description;

    public Plans(CreatePlanCommand command) {
        this.name = command.name();
        this.price_per_employ = command.price_per_employ();
        this.max_employees = command.max_employees();
        this.description = command.description();
    }
}
