package com.identity.identity_service.iam.application.internal.commandServices;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.iam.application.internal.outboundservices.acl.ExternalClientService;
import com.identity.identity_service.iam.application.internal.outboundservices.hashing.HashingService;
import com.identity.identity_service.iam.application.internal.outboundservices.tokens.TokenService;
import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.commands.CreateManagerCommand;
import com.identity.identity_service.iam.domain.model.commands.SignInCommand;
import com.identity.identity_service.iam.domain.model.commands.SignUpEmployeeCommand;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.domain.model.valueObjects.Roles;
import com.identity.identity_service.iam.domain.services.UserCommandService;
import com.identity.identity_service.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.identity.identity_service.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final TokenService tokenService;
    private final HashingService hashingService;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ExternalClientService externalClientService;

    public UserCommandServiceImpl(
            TokenService tokenService,
            HashingService hashingService,
            RoleRepository roleRepository,
            UserRepository userRepository,
            ExternalClientService externalClientService) {
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.externalClientService = externalClientService;
    }

    @Override
    public List<User> handleOnApplicationReady(List<CreateManagerCommand> commands, List<Roles> roles) {

        var storedRoles = new ArrayList<Role>();
        for (Roles it : roles) {
            var role = roleRepository.findByName(it);
            if (role.isEmpty()){
                break;
            }
            storedRoles.add(role.get());
        }

        if (storedRoles.isEmpty()){
            return Collections.emptyList();
        }

        return commands.stream().map(command -> {
            return new User(command.email(),hashingService.encode(command.password()),storedRoles,true,null);
        }).collect(Collectors.toList());
    }

    private List<Roles> selectRole(String area){
        var rolesList = new ArrayList<Roles>();
        switch (area.toUpperCase()){
            case "GENERAL_MANAGEMENT","ADMINISTRATION","HUMAN_RESOURCES","INFORMATION_TECHNOLOGY"->rolesList.addAll(List.of(Roles.ROLE_ADMIN,Roles.ROLE_CLIENT));
            default -> rolesList.add(Roles.ROLE_CLIENT);
        }
        return rolesList;
    }

    private User createUser(String email, Employee employee) {

        /*userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists");
        });*/

        var roles = selectRole(employee.getArea().name());
        var storedRoles = new ArrayList<Role>();

        for (Roles it : roles) {
            var role = roleRepository.findByName(it);
            if (role.isEmpty()){
                break;
            }
            storedRoles.add(role.get());
        }

        String password = employee.getIdentity().dni();

        return new User(email, hashingService.encode(password), storedRoles,true,employee);

    }

    @Override
    public Optional<User> handle(SignUpEmployeeCommand command) {

        //external service de empleado para crear y devolver objeto Employee
        var employee = externalClientService.createEmployee(
                command.name(), command.lastName(), command.age(), command.dni(),
                command.gender(), command.location(), command.phoneNumber(), command.workEmail(),
                command.personalEmail(), command.occupation(), command.area()
        );

        if (employee.isEmpty())return Optional.empty();

        var user = this.createUser(command.workEmail(), employee.get());
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public List<User> handle(List<SignUpEmployeeCommand> commands) {
        var employeeList = externalClientService.createEmployees(commands);
        if (employeeList.isEmpty())return Collections.emptyList();

        var usersList = employeeList.stream().map(employee -> this.createUser(employee.getContactInfo().workEmail(), employee)).collect(Collectors.toList());
        return userRepository.saveAll(usersList);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())return Optional.empty();
        if (!hashingService.matches(command.password(),user.get().getPassword()))return Optional.empty();
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(),token));
    }
}
