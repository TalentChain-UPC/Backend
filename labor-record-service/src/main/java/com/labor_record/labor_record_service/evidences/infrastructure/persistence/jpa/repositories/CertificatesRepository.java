package com.labor_record.labor_record_service.evidences.infrastructure.persistence.jpa.repositories;

import com.labor_record.labor_record_service.evidences.domain.model.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificate, Long> {
}
