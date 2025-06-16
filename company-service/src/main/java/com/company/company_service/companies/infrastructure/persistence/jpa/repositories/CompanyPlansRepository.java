package com.company.company_service.companies.infrastructure.persistence.jpa.repositories;

import com.company.company_service.companies.domain.model.entities.Company_Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPlansRepository extends JpaRepository<Company_Plans, Long> {
}
