package com.company.company_service.companies.application.internal;

import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.domain.model.queries.GetAllCompaniesQuery;
import com.company.company_service.companies.domain.model.queries.GetCompanyByIdQuery;
import com.company.company_service.companies.domain.services.CompaniesQueryService;
import com.company.company_service.companies.infrastructure.persistence.jpa.repositories.CompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesQueryServiceImpl implements CompaniesQueryService {
    private final CompaniesRepository companiesRepository;
    public CompaniesQueryServiceImpl(final CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }
    @Override
    public Optional<Companies> handle(GetCompanyByIdQuery query) {
        return companiesRepository.findById(query.id());
    }
    @Override
    public List<Companies> handle(GetAllCompaniesQuery query) {
        return companiesRepository.findAll();
    }
}
