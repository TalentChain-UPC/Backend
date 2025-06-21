package com.company.company_service.companies.application.internal;

import com.company.company_service.companies.domain.model.commands.CreateCompanyCommand;
import com.company.company_service.companies.domain.model.commands.DeleteCompanyCommand;
import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.domain.services.CompaniesCommandService;
import com.company.company_service.companies.infrastructure.persistence.jpa.repositories.CompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompaniesCommandServiceImpl implements CompaniesCommandService {
    private final CompaniesRepository companiesRepository;
    public CompaniesCommandServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }
    @Override
    public Optional<Companies> handle(CreateCompanyCommand command) {
        var companies = new Companies(command);
        companiesRepository.save(companies);
        return Optional.of(companies);
    }

    @Override
    public Optional<Companies> handle(DeleteCompanyCommand command) {
        var companies = companiesRepository.findById(command.id());
        if (companies.isEmpty()) {
            throw new IllegalArgumentException("Companies with id " + command.id() + " not found");
        }
        companiesRepository.delete(companies.get());
        return companies;
    }
}