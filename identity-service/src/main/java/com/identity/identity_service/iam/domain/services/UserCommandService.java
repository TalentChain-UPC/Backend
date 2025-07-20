package com.identity.identity_service.iam.domain.services;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.commands.CreateCompanyCommand;
import com.identity.identity_service.iam.domain.model.commands.CreateManagerCommand;
import com.identity.identity_service.iam.domain.model.commands.SignInCommand;
import com.identity.identity_service.iam.domain.model.commands.SignUpEmployeeCommand;
import com.identity.identity_service.iam.domain.model.valueObjects.Roles;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpEmployeeCommand command, CreateCompanyCommand companyCommand);
    List<User> handle(List<SignUpEmployeeCommand> commands, CreateCompanyCommand companyCommand);
    Optional<ImmutablePair<User,String>> handle(SignInCommand command);
    List<User> handleOnApplicationReady(List<CreateManagerCommand> commands, List<Roles> roles);
}
