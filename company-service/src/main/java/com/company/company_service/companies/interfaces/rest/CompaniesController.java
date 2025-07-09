package com.company.company_service.companies.interfaces.rest;

import com.company.company_service.companies.domain.model.commands.DeleteCompanyCommand;
import com.company.company_service.companies.domain.model.entities.Companies;
import com.company.company_service.companies.domain.model.queries.ExistsByCompanyIdQuery;
import com.company.company_service.companies.domain.model.queries.GetAllCompaniesQuery;
import com.company.company_service.companies.domain.model.queries.GetCompanyByIdQuery;
import com.company.company_service.companies.domain.services.CompaniesCommandService;
import com.company.company_service.companies.domain.services.CompaniesQueryService;
import com.company.company_service.companies.interfaces.rest.resources.CompaniesResource;
import com.company.company_service.companies.interfaces.rest.resources.CreateCompaniesResource;
import com.company.company_service.companies.interfaces.rest.transform.CompaniesResourceFromEntityAssembler;
import com.company.company_service.companies.interfaces.rest.transform.CreateCompaniesCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/companies")
public class CompaniesController {
    private final CompaniesCommandService companiesCommandService;
    private final CompaniesQueryService companiesQueryService;
    public CompaniesController(CompaniesCommandService companiesCommandService, CompaniesQueryService companiesQueryService) {
        this.companiesCommandService = companiesCommandService;
        this.companiesQueryService = companiesQueryService;
    }
    @PostMapping("/create-by-ruc")
    public ResponseEntity<Long> createCompanyByRUCIfNotExists(
            @RequestBody CreateCompaniesResource createCompaniesResource) {
        var createCompanyCommand = CreateCompaniesCommandFromResourceAssembler.toCommandFromResource(createCompaniesResource);
        var companies = companiesCommandService.handle(createCompanyCommand);
        //if (companies.isEmpty()) return ResponseEntity.ok();
        return new ResponseEntity<>(companies.get().getId(), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CompaniesResource>> getAllCompanies() {
        var companies = companiesQueryService.handle(new GetAllCompaniesQuery());
        var companiesResource = companies.stream().map(CompaniesResourceFromEntityAssembler::transformResourceFromEntity).toList();
        return ResponseEntity.ok(companiesResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompaniesResource> getCompaniesById(@PathVariable Long id) {
        var companies = companiesQueryService.handle(new GetCompanyByIdQuery(id));
        if (companies.isEmpty()) {
            throw new IllegalArgumentException("Company with id " + id + " not found");
        }
        var companiesResource = CompaniesResourceFromEntityAssembler.transformResourceFromEntity(companies.get());
        return ResponseEntity.ok(companiesResource);
    }
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companiesQueryService.handle(new ExistsByCompanyIdQuery(id)));
    }
    /*@PostMapping
    public ResponseEntity<CompaniesResource> createCompanies(@RequestBody CreateCompaniesResource resource) {
        var createCompanyCommand = CreateCompaniesCommandFromResourceAssembler.toCommandFromResource(resource);
        var companies = companiesCommandService.handle(createCompanyCommand);
        if (companies.isEmpty()) return ResponseEntity.badRequest().build();
        var companiesResource = CompaniesResourceFromEntityAssembler.transformResourceFromEntity(companies.get());
        return new ResponseEntity<CompaniesResource>(companiesResource, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CompaniesResource> deleteCompanies(@PathVariable Long id) {
        var deleteCompanyCommand = new DeleteCompanyCommand(id);
        var companies = companiesCommandService.handle(deleteCompanyCommand);
        if (companies.isEmpty()) return ResponseEntity.badRequest().build();
        var companiesResource = CompaniesResourceFromEntityAssembler.transformResourceFromEntity(companies.get());
        return ResponseEntity.ok(companiesResource);
    }*/
}
