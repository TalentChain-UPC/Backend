package com.company.company_service.companies.interfaces.rest;

import com.company.company_service.companies.domain.model.commands.DeletePlanCommand;
import com.company.company_service.companies.domain.model.queries.GetAllPlansQuery;
import com.company.company_service.companies.domain.model.queries.GetPlanByIdQuery;
import com.company.company_service.companies.domain.services.CompaniesQueryService;
import com.company.company_service.companies.domain.services.PlansCommandService;
import com.company.company_service.companies.domain.services.PlansQueryService;
import com.company.company_service.companies.interfaces.rest.resources.CreatePlansResource;
import com.company.company_service.companies.interfaces.rest.resources.PlansResource;
import com.company.company_service.companies.interfaces.rest.transform.CreatePlansCommandFromResourceAssembler;
import com.company.company_service.companies.interfaces.rest.transform.PlansResourceFromEntityAssembler;
import jakarta.ws.rs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/plans")
public class PlansController {
    private final PlansCommandService plansCommandService;
    private final PlansQueryService plansQueryService;
    private final CompaniesQueryService companiesQueryService;

    public PlansController(PlansCommandService plansCommandService, PlansQueryService plansQueryService, CompaniesQueryService companiesQueryService) {
        this.plansCommandService = plansCommandService;
        this.plansQueryService = plansQueryService;
        this.companiesQueryService = companiesQueryService;
    }
    @GetMapping
    public ResponseEntity<List<PlansResource>> getAllPlans() {
        var plans = plansQueryService.handle(new GetAllPlansQuery());
        var plansResource = plans.stream().map(PlansResourceFromEntityAssembler::transformResourceFromEntity).toList();
        return ResponseEntity.ok(plansResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlansResource> getPlanById(@PathVariable Long id) {
        var plans = plansQueryService.handle(new GetPlanByIdQuery(id));
        if (plans.isEmpty()) {
            throw new IllegalArgumentException("Plan with id " + id + " not found");
        }
        var plansResource = PlansResourceFromEntityAssembler.transformResourceFromEntity(plans.get());
        return ResponseEntity.ok(plansResource);
    }
    @PostMapping
    public ResponseEntity<PlansResource> createPlan(@RequestBody CreatePlansResource resource) {
        var createPlanCommand = CreatePlansCommandFromResourceAssembler.toCommandFromResource(resource);
        var plans = plansCommandService.handle(createPlanCommand);
        if (plans.isEmpty()) return ResponseEntity.badRequest().build();
        var plansResource = PlansResourceFromEntityAssembler.transformResourceFromEntity(plans.get());
        return new ResponseEntity<PlansResource>(plansResource, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PlansResource> deletePlanById(@PathVariable Long id) {
        var deletePlanCommand = new DeletePlanCommand(id);
        var plans = plansCommandService.handle(deletePlanCommand);
        if (plans.isEmpty()) return ResponseEntity.badRequest().build();
        var plansResource = PlansResourceFromEntityAssembler.transformResourceFromEntity(plans.get());
        return ResponseEntity.ok(plansResource);
    }
}
