package com.identity.identity_service.clients.application.internal.commandServices;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.domain.model.commands.CreateEmployeeCommand;
import com.identity.identity_service.clients.domain.model.valueObjects.Area;
import com.identity.identity_service.clients.domain.services.EmployeeCommandService;
import com.identity.identity_service.clients.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private boolean areaValid(String cmdArea){
        Area area;
        try {
            area = Area.valueOf(cmdArea);
        }catch (IllegalArgumentException e){
            //LOG
            return false;
        }
        return true;
    }

    @Override
    public Optional<Employee> handle(CreateEmployeeCommand command) {
        if (!areaValid(command.area()))return Optional.empty();
        var area = Area.valueOf(command.area());
        var employee = new Employee(command,area);
        employeeRepository.save(employee);
        return Optional.of(employee);
    }

    @Override
    public List<Employee> handle(List<CreateEmployeeCommand> commands) {

        var allValid = commands.stream().allMatch(cmd -> areaValid(cmd.area()));

        if (!allValid)return Collections.emptyList();

        List<Employee> employeeList = commands.stream()
                .map(command -> {
                    var area = Area.valueOf(command.area());
                    return new Employee(command,area);
                })
                .collect(Collectors.toList());
        employeeRepository.saveAll(employeeList);
        return employeeList;
    }
}
