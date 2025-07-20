package com.identity.identity_service.clients.interfaces.rest;

import com.identity.identity_service.clients.domain.model.queries.ExistsByEmployeeIdQuery;
import com.identity.identity_service.clients.domain.model.queries.GetEmployeeByIdQuery;
import com.identity.identity_service.clients.domain.model.queries.GetEmployeesByCompanyIdQuery;
import com.identity.identity_service.clients.domain.services.EmployeeQueryService;
import com.identity.identity_service.clients.interfaces.rest.resources.EmployeeResource;
import com.identity.identity_service.clients.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeQueryService employeeQueryService;

    public EmployeeController(EmployeeQueryService employeeQueryService) {
        this.employeeQueryService = employeeQueryService;
    }

    //@PreAuthorize(value = "hasAnyRole('COMPANY','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResource> getEmployeeById(@PathVariable Long id){
        var employee = employeeQueryService.handle(new GetEmployeeByIdQuery(id));
        if (employee.isEmpty())return ResponseEntity.notFound().build();
        var resource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("{id}/exists")
    public ResponseEntity<Boolean> existsEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(
                employeeQueryService.handle(new ExistsByEmployeeIdQuery(id))
        );
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<EmployeeResource>> getEmployeesByCompanyId(@PathVariable Long companyId){
        var employees = employeeQueryService.handle(new GetEmployeesByCompanyIdQuery(companyId));
        var resources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
