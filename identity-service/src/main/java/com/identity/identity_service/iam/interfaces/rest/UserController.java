package com.identity.identity_service.iam.interfaces.rest;

import com.identity.identity_service.iam.domain.model.queries.GetAllUsers;
import com.identity.identity_service.iam.domain.services.UserCommandService;
import com.identity.identity_service.iam.domain.services.UserQueryService;
import com.identity.identity_service.iam.interfaces.rest.resources.CreateBatchByCompanyResource;
import com.identity.identity_service.iam.interfaces.rest.resources.CreateByCompanyResource;
import com.identity.identity_service.iam.interfaces.rest.resources.RegisterEmployeeResource;
import com.identity.identity_service.iam.interfaces.rest.resources.UserResource;
import com.identity.identity_service.iam.interfaces.rest.transform.CreateCompanyCommandFromResourceAssembler;
import com.identity.identity_service.iam.interfaces.rest.transform.SignUpEmployeeCommandFromResourceAssembler;
import com.identity.identity_service.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.identity.identity_service.shared.interfaces.rest.resource.MessageResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(
            UserCommandService userCommandService,
            UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping("/create-batch")
    //public ResponseEntity<MessageResource> createMultipleClients(@RequestBody List<RegisterEmployeeResource> resources){
    public ResponseEntity<MessageResource> createMultipleClients(@RequestBody CreateBatchByCompanyResource resource){
        var commands = resource.employees().stream()
                .map(SignUpEmployeeCommandFromResourceAssembler::toCommandFromResource)
                .collect(Collectors.toList());
        var companyCommand = CreateCompanyCommandFromResourceAssembler
                .toCommandFromResource(resource.name(), resource.ruc(), resource.sector());
        var users = userCommandService.handle(commands, companyCommand);
        if(users.isEmpty())return ResponseEntity.badRequest().build();
        var message = "Se registraron "+users.size()+" empleados exitosamente";
        return ResponseEntity.ok(new MessageResource(message));
    }

    @PostMapping("/create")
    //public ResponseEntity<MessageResource> createClient(@RequestBody RegisterEmployeeResource resource){
    public ResponseEntity<MessageResource> createClient(@RequestBody CreateByCompanyResource resource){
        var command = SignUpEmployeeCommandFromResourceAssembler.toCommandFromResource(resource.employee());
        var companyCommand = CreateCompanyCommandFromResourceAssembler
                .toCommandFromResource(resource.name(), resource.ruc(), resource.sector());
        var user = userCommandService.handle(command, companyCommand);
        if(user.isEmpty())return ResponseEntity.badRequest().build();
        var message = "Se registró un nuevo empleado";
        return ResponseEntity.ok(new MessageResource(message));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/manager-view")
    public ResponseEntity<List<UserResource>> getAllUsers(){
        var users = userQueryService.handle(new GetAllUsers());
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    //public ResponseEntity<UserResource> getUsersByCompanyId(){}

}
