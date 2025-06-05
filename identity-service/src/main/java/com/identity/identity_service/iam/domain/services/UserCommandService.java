package com.identity.identity_service.iam.domain.services;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.commands.SignInCommand;
import com.identity.identity_service.iam.domain.model.commands.SignUpEmployeeCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpEmployeeCommand command);
    List<User> handle(List<SignUpEmployeeCommand> commands);
    Optional<ImmutablePair<User,String>> handle(SignInCommand command);
}
