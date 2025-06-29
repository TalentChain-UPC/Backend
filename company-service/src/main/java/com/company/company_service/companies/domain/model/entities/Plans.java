package com.company.company_service.companies.domain.model.entities;

import com.company.company_service.companies.domain.model.commands.CreatePlanCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/
@Entity
@Table(name = "plans")
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

    public Plans() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_per_employ() {
        return price_per_employ;
    }

    public void setPrice_per_employ(Double price_per_employ) {
        this.price_per_employ = price_per_employ;
    }

    public Integer getMax_employees() {
        return max_employees;
    }

    public void setMax_employees(Integer max_employees) {
        this.max_employees = max_employees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
