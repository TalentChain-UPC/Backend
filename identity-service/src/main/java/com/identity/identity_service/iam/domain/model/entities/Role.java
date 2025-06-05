package com.identity.identity_service.iam.domain.model.entities;

import com.identity.identity_service.iam.domain.model.valueObjects.Roles;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private Roles name;

    public Role() {}
    public Role(Roles name) {
        this.name = name;
    }
}
