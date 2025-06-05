package com.identity.identity_service.iam.interfaces.rest;

import com.identity.identity_service.iam.domain.services.UserCommandService;
import com.identity.identity_service.iam.interfaces.rest.resources.RegisterEmployeeResource;
import com.identity.identity_service.iam.interfaces.rest.transform.SignUpEmployeeCommandFromResourceAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserCommandService userCommandService;

    public UserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/batch")
    public ResponseEntity<String> createMultipleClients(@RequestBody List<RegisterEmployeeResource> resources){
        var commands = resources.stream()
                .map(SignUpEmployeeCommandFromResourceAssembler::toCommandFromResource)
                .collect(Collectors.toList());
        var users = userCommandService.handle(commands);
        if(users.isEmpty())return ResponseEntity.badRequest().build();
        var message = "Se registraron "+users.size()+" empleados exitosamente";
        return ResponseEntity.ok().body(message);
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody RegisterEmployeeResource resource){
        var command = SignUpEmployeeCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);
        if(user.isEmpty())return ResponseEntity.badRequest().build();
        var message = "Se registró un nuevo empleado";
        return ResponseEntity.ok().body(message);
    }

}
