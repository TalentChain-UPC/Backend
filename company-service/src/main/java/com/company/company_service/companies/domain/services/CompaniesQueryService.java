package com.company.company_service.companies.domain.services;

import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.domain.model.queries.GetAllCompaniesQuery;
import com.company.company_service.companies.domain.model.queries.GetCompanyByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompaniesQueryService {
    Optional<Companies> handle(GetCompanyByIdQuery query);
    List<Companies> handle(GetAllCompaniesQuery query);
}
