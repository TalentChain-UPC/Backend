package com.company.company_service.companies.domain.services;

import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.domain.model.entities.Plans;
import com.company.company_service.companies.domain.model.queries.GetAllCompaniesQuery;
import com.company.company_service.companies.domain.model.queries.GetAllPlansQuery;
import com.company.company_service.companies.domain.model.queries.GetCompanyByIdQuery;
import com.company.company_service.companies.domain.model.queries.GetPlanByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlansQueryService {
    Optional<Plans> handle(GetPlanByIdQuery query);
    List<Plans> handle(GetAllPlansQuery query);
}
