package com.identity.identity_service.iam.application.internal.outboundservices.acl;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.interfaces.acl.EmployeeContextFacade;
import com.identity.identity_service.clients.interfaces.acl.dto.EmployeeDTO;
import com.identity.identity_service.iam.domain.model.commands.SignUpEmployeeCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExternalClientService {
    private final EmployeeContextFacade employeeContextFacade;

    public ExternalClientService(EmployeeContextFacade employeeContextFacade) {
        this.employeeContextFacade = employeeContextFacade;
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
        return employeeContextFacade.createEmployee(
                name,
                lastName,
                age,
                dni,
                gender,
                location,
                phoneNumber,
                workEmail,
                personalEmail,
                occupation,
                area
        );

    }

    public List<Employee> createEmployees(List<SignUpEmployeeCommand> commands){
        var employeeDTOList = commands.stream().map(command -> new EmployeeDTO(
                command.name(), command.lastName(), command.age(), command.dni(),
                command.gender(), command.location(), command.phoneNumber(),
                command.workEmail(), command.personalEmail(),
                command.occupation(), command.area())).collect(Collectors.toList());
        return employeeContextFacade.createEmployees(employeeDTOList);
    }
}
