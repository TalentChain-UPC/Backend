package com.identity.identity_service.iam.domain.model.aggregates;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.iam.domain.model.entities.Role;
import com.identity.identity_service.shared.model.aggregate.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*@Getter
@Setter*/
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",unique = true)
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
        this.roles = new HashSet<>();
        addRoles(roles);
        this.isActive = isActive;
        this.employee = employee;
    }

    public void addRoles(List<Role> roles) {
        System.out.println(roles.size());
        this.roles.addAll(roles);
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
