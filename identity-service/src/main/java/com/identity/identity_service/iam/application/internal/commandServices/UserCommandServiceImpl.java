package com.identity.identity_service.iam.application.internal.commandServices;

import com.identity.identity_service.clients.application.internal.outboundservices.acl.ExternalCompanyService;
import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.iam.application.internal.outboundservices.acl.ExternalClientService;
import com.identity.identity_service.iam.application.internal.outboundservices.hashing.HashingService;
import com.identity.identity_service.iam.application.internal.outboundservices.tokens.TokenService;
import com.identity.identity_service.iam.domain.exceptions.IncorrectPasswordException;
import com.identity.identity_service.iam.domain.exceptions.UserAlreadyExistsException;
import com.identity.identity_service.iam.domain.exceptions.UserNotFoundException;
import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.commands.CreateCompanyCommand;
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

import java.util.*;
import java.util.stream.Collectors;

import static com.identity.identity_service.iam.domain.model.valueObjects.AdminOccupationRules.ADMIN_OCCUPATIONS_BY_AREA;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final TokenService tokenService;
    private final HashingService hashingService;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ExternalClientService externalClientService;
    private final ExternalCompanyService externalCompanyService;

    private static final Set<String> itAdminOccupations = Set.of(
            "soporte tecnico","administrador de sistemas", "soporte", "mesa de ayuda", "help desk"
    );

    public UserCommandServiceImpl(
            TokenService tokenService,
            HashingService hashingService,
            RoleRepository roleRepository,
            UserRepository userRepository,
            ExternalClientService externalClientService,
            ExternalCompanyService externalCompanyService) {
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.externalClientService = externalClientService;
        this.externalCompanyService = externalCompanyService;
    }

    @Override
    public List<User> handleOnApplicationReady(List<CreateManagerCommand> commands, List<Roles> roles) {

        var allExist = commands.stream().allMatch(cmd -> userRepository.findByEmail(cmd.email()).isPresent());

        if (allExist)return Collections.emptyList();

        List<Role> storedRoles = new ArrayList<>();
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

        List<User> userList =  commands.stream()
                .map(command -> new User(
                        command.email(),
                        hashingService.encode(command.password()),
                        storedRoles,
                        true,
                        null))
                .collect(Collectors.toList());
        userRepository.saveAll(userList);
        return userList;
    }

    private List<Roles> selectRole(String area, String occupation){
        var rolesList = new ArrayList<Roles>();

        String normalizedArea = area.toUpperCase();
        String normalizedOccupation = occupation.toLowerCase().trim();

        if(ADMIN_OCCUPATIONS_BY_AREA.getOrDefault(normalizedArea, Set.of()).contains(normalizedOccupation)){
            rolesList.addAll(List.of(Roles.COMPANY,Roles.EMPLOYEE));
        }else {
            rolesList.add(Roles.EMPLOYEE);
        }

        return rolesList;
    }

    private User createUser(String email, Employee employee) {
        userRepository.findByEmail(email).ifPresent(user->{
            throw new UserAlreadyExistsException("User already exists");
        });

        var roles = selectRole(employee.getArea().name(),employee.getOccupation());
        var storedRoles = new ArrayList<Role>();

        for (Roles it : roles) {
            var role = roleRepository.findByName(it);
            role.ifPresent(storedRoles::add);
        }

        String password = employee.getIdentity().dni();

        return new User(email, hashingService.encode(password), storedRoles,true,employee);

    }

    private Long createCompanyByRUC(CreateCompanyCommand command) {
        return externalCompanyService.createIfCompanyNotExists(command);
    }

    @Override
    public Optional<User> handle(SignUpEmployeeCommand command, CreateCompanyCommand companyCommand) {
        Long companyId = createCompanyByRUC(companyCommand);
        if(companyId==0L){
            return Optional.empty();
        }
        var employee = externalClientService.createEmployee(
                command.name(), command.lastName(), command.age(), command.dni(),
                command.gender(), command.location(), command.phoneNumber(), command.workEmail(),
                command.personalEmail(), command.occupation(), command.area(), companyId
        );

        if (employee.isEmpty())return Optional.empty();

        var user = this.createUser(command.workEmail(), employee.get());
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public List<User> handle(List<SignUpEmployeeCommand> commands, CreateCompanyCommand companyCommand) {
        Long companyId = createCompanyByRUC(companyCommand);
        if(companyId==0L){
            return Collections.emptyList();
        }

        var employeeList = externalClientService.createEmployees(commands,companyId);
        if (employeeList.isEmpty())return Collections.emptyList();

        List<User> usersList = employeeList.stream().map(employee -> this.createUser(employee.getContactInfo().workEmail(), employee)).collect(Collectors.toList());
        return userRepository.saveAll(usersList);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())throw new UserNotFoundException("User not found");
        if (!hashingService.matches(command.password(),user.get().getPassword())){
            throw new IncorrectPasswordException("Incorrect password");
        }
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(),token));
    }
}
