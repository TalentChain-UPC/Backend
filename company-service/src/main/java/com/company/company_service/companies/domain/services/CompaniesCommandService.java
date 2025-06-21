package com.company.company_service.companies.domain.services;

import com.company.company_service.companies.domain.model.commands.CreateCompanyCommand;
import com.company.company_service.companies.domain.model.commands.DeleteCompanyCommand;
import com.company.company_service.companies.domain.model.entities.Companies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompaniesCommandService {
    Optional<Companies> handle(CreateCompanyCommand command);
    Optional<Companies> handle(DeleteCompanyCommand command);
}
