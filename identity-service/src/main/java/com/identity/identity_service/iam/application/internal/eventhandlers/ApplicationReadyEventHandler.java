package com.identity.identity_service.iam.application.internal.eventhandlers;

import com.identity.identity_service.iam.domain.model.commands.CreateManagerCommand;
import com.identity.identity_service.iam.domain.model.commands.SeedRolesCommand;
import com.identity.identity_service.iam.domain.model.valueObjects.Roles;
import com.identity.identity_service.iam.domain.services.RoleCommandService;
import com.identity.identity_service.iam.domain.services.UserCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationReadyEventHandler {

    @Value("${managers-account.temporary-pswd}")
    private String temporaryPassword;

    private final RoleCommandService roleCommandService;
    private final UserCommandService userCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(
            RoleCommandService roleCommandService,
            UserCommandService userCommandService) {
        this.roleCommandService = roleCommandService;
        this.userCommandService = userCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);
        LOGGER.info("Roles Seeding Completed");
        var commands = Arrays.asList(
                new CreateManagerCommand("gerardo@skillLedger.com",temporaryPassword,true,null),
                new CreateManagerCommand("luis@skillLedger.com",temporaryPassword,true,null),
                new CreateManagerCommand("jeremy@skillLedger.com",temporaryPassword,true,null),
                new CreateManagerCommand("piero@skillLedger.com",temporaryPassword,true,null),
                new CreateManagerCommand("brayan@skillLedger.com",temporaryPassword,true,null)
        );
        var managers = userCommandService.handleOnApplicationReady(commands, List.of(Roles.MANAGER));
        LOGGER.info("Managers Seeding Completed");
    }
}
