package com.company.company_service.companies.application.internal;

import com.company.company_service.companies.domain.model.entities.Plans;
import com.company.company_service.companies.domain.model.queries.GetAllPlansQuery;
import com.company.company_service.companies.domain.model.queries.GetPlanByIdQuery;
import com.company.company_service.companies.domain.services.PlansQueryService;
import com.company.company_service.companies.infrastructure.persistence.jpa.repositories.PlansRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlansQueryServiceImpl implements PlansQueryService {
    private final PlansRepository plansRepository;
    public PlansQueryServiceImpl(final PlansRepository plansRepository) {
        this.plansRepository = plansRepository;
    }
    @Override
    public Optional<Plans> handle(GetPlanByIdQuery query) { return plansRepository.findById(query.id()); }
    @Override
    public List<Plans> handle(GetAllPlansQuery query) { return plansRepository.findAll(); }
}
