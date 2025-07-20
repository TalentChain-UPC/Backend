package com.identity.identity_service.iam.infrastructure.bcrypt;

import com.identity.identity_service.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
