package com.company.company_service.companies.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompanyPlansId implements Serializable {

    @Column(name = "Plans_id")
    private Long Plans_id;

    @Column(name = "Companies_id")
    private Long Companies_id;

    public CompanyPlansId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyPlansId that = (CompanyPlansId) o;
        return Objects.equals(Plans_id, that.Plans_id) &&
                Objects.equals(Companies_id, that.Companies_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Plans_id, Companies_id);
    }

    public Long getPlans_id() {
        return Plans_id;
    }

    public void setPlans_id(Long plans_id) {
        this.Plans_id = plans_id;
    }

    public Long getCompanies_id() {
        return Companies_id;
    }

    public void setCompanies_id(Long companies_id) {
        this.Companies_id = companies_id;
    }


}
