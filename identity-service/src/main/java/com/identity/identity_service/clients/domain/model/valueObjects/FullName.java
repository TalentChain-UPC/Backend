package com.identity.identity_service.clients.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FullName(String name, String lastName) {
}
