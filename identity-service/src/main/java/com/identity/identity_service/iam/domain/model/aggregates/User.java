package com.identity.identity_service.iam.domain.model.aggregates;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.iam.domain.model.valueObjects.Roles;
import com.identity.identity_service.shared.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",unique = true,nullable = false)
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {}

    public User(String email, String password, List<Role> roles, Boolean isActive, Employee employee) {
        this.email = email;
        this.password = password;
        addRoles(roles);
        this.isActive = isActive;
        this.employee = employee;
    }

    public void addRoles(List<Role> roles) {
        this.roles.addAll(roles);
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
