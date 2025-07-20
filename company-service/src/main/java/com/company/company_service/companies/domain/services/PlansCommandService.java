package com.company.company_service.companies.domain.services;

import com.company.company_service.companies.domain.model.commands.CreatePlanCommand;
import com.company.company_service.companies.domain.model.commands.DeletePlanCommand;
import com.company.company_service.companies.domain.model.entities.Plans;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PlansCommandService {
    Optional<Plans> handle(CreatePlanCommand command);
    Optional<Plans> handle(DeletePlanCommand command);
}
