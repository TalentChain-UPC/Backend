package com.company.company_service.companies.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "price_per_employ", precision = 5, scale = 2)
    private Double price_per_employ;

    @Column(name = "max_employees")
    private Integer max_employees;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "plan")
    private List<Company_Plans> companyPlans;
}
