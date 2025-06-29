package com.company.company_service.companies.infrastructure.persistence.jpa.repositories;

import com.company.company_service.companies.domain.model.entities.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {
    Boolean existsByRuc(String ruc);
}
