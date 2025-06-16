package com.company.company_service.companies.domain.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "company_plans")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company_Plans {

    @EmbeddedId
    private CompanyPlansId id;

    @ManyToOne
    @MapsId("companiesId")
    @JoinColumn(name = "Companies_id")
    private Companies company;

    @ManyToOne
    @MapsId("plansId")
    @JoinColumn(name = "Plans_id")
    private Plans plan;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, INACTIVE, EXPIRED
    }
}
