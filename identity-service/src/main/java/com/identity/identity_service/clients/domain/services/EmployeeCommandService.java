package com.identity.identity_service.clients.domain.services;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.domain.model.commands.CreateEmployeeCommand;

import java.util.List;
import java.util.Optional;

public interface EmployeeCommandService {
    Optional<Employee> handle(CreateEmployeeCommand command);
    List<Employee> handle(List<CreateEmployeeCommand> commands);
}
