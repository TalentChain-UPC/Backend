package com.identity.identity_service.iam.domain.services;

import com.identity.identity_service.iam.domain.model.aggregates.User;
import com.identity.identity_service.iam.domain.model.queries.GetAllUsers;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUsers query);
}
