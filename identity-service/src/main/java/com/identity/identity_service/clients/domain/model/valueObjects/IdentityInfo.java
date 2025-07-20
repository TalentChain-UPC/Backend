package com.identity.identity_service.clients.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record IdentityInfo(String age, String dni, String gender, String location) {
}
