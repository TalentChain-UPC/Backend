package com.identity.identity_service.iam.application.internal.queryServices;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.queries.GetAllUsers;
import com.identity.identity_service.iam.domain.services.UserQueryService;
import com.identity.identity_service.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsers query) {
        return userRepository.findAll();
    }
}
