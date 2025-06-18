package com.identity.identity_service.clients.interfaces.acl;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.domain.model.commands.CreateEmployeeCommand;
import com.identity.identity_service.clients.domain.services.EmployeeCommandService;
import com.identity.identity_service.clients.interfaces.acl.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeContextFacade {
    private final EmployeeCommandService employeeCommandService;

    public EmployeeContextFacade(EmployeeCommandService employeeCommandService) {
        this.employeeCommandService = employeeCommandService;
    }

    public Optional<Employee> createEmployee(
            String name,
            String lastName,
            String age,
            String dni,
            String gender,
            String location,
            String phoneNumber,
            String workEmail,
            String personalEmail,
            String occupation,
            String area
    ){
        return employeeCommandService.handle(new CreateEmployeeCommand(
                name, lastName, age, dni, gender, location, phoneNumber,
                workEmail, personalEmail, occupation, area
        ));
    }

    public List<Employee> createEmployees(List<EmployeeDTO> employeeDTOList){
        var commands = employeeDTOList.stream().map(dto -> new CreateEmployeeCommand(
                dto.name(), dto.lastName(), dto.age(), dto.dni(),
                dto.gender(), dto.location(), dto.phoneNumber(),
                dto.workEmail(), dto.personalEmail(),
                dto.occupation(), dto.area())).collect(Collectors.toList());
        return employeeCommandService.handle(commands);
    }

}
