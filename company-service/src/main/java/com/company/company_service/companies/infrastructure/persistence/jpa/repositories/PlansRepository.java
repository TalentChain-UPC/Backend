package com.company.company_service.companies.infrastructure.persistence.jpa.repositories;

import com.company.company_service.companies.domain.model.entities.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepository extends JpaRepository<Plans, Long> {
}
