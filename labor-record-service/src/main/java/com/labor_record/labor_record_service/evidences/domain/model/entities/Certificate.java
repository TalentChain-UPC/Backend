package com.labor_record.labor_record_service.evidences.domain.model.entities;

import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateCertificateCommand;
import com.labor_record.labor_record_service.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

@Entity
public class Certificate extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String url;

    public Certificate(CreateCertificateCommand command) {
        this.url=command.url();
    }
    public Certificate() {
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
