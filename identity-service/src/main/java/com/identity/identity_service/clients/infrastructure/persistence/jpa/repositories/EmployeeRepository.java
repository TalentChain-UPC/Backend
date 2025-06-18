package com.identity.identity_service.clients.infrastructure.persistence.jpa.repositories;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByContactInfo_WorkEmail(String workEmail);
}
