package com.company.company_service.companies.application.internal;

import com.company.company_service.companies.domain.model.commands.CreatePlanCommand;
import com.company.company_service.companies.domain.model.commands.DeletePlanCommand;
import com.company.company_service.companies.domain.model.entities.Plans;
import com.company.company_service.companies.domain.services.PlansCommandService;
import com.company.company_service.companies.infrastructure.persistence.jpa.repositories.PlansRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlansCommandServiceImpl implements PlansCommandService {
    private final PlansRepository plansRepository;
    public PlansCommandServiceImpl(PlansRepository plansRepository) {
        this.plansRepository = plansRepository;
    }
    @Override
    public Optional<Plans> handle(CreatePlanCommand command) {
        var plans = new Plans(command);
        plansRepository.save(plans);
        return Optional.of(plans);
    }

    @Override
    public Optional<Plans> handle(DeletePlanCommand command) {
        var plan = plansRepository.findById(command.id());
        if (plan.isEmpty()) {
            throw new IllegalArgumentException("Plan with id " + command.id() + " not found");
        }
        plansRepository.delete(plan.get());
        return plan;
    }
}
