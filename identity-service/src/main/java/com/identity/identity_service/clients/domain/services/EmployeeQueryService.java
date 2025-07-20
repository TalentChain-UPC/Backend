package com.identity.identity_service.clients.domain.services;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.domain.model.queries.ExistsByEmployeeIdQuery;
import com.identity.identity_service.clients.domain.model.queries.GetEmployeeByIdQuery;
import com.identity.identity_service.clients.domain.model.queries.GetEmployeesByCompanyIdQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    Boolean handle(ExistsByEmployeeIdQuery query);
    List<Employee> handle(GetEmployeesByCompanyIdQuery query);
}
