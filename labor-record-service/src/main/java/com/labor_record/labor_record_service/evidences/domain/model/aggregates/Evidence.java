package com.labor_record.labor_record_service.evidences.domain.model.aggregates;

import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.domain.model.entities.Certificate;
import com.labor_record.labor_record_service.evidences.domain.model.valueObjects.Type;
import com.labor_record.labor_record_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
public class Evidence extends AuditableAbstractAggregateRoot<Evidence> {
    @Column(nullable = false)
    Long employeeId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean validated;

    @Column(nullable = false, name = "requires_certification")
    private boolean requireCertification;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "certificate_id",unique = true)
    private Certificate certificate;

    @Column(columnDefinition = "json", nullable = false)
    private String data;

    public Evidence(CreateEvidenceCommand command, Type type, Certificate certificate) {
        this.employeeId= command.employeeId();
        this.type = type;
        this.description=command.description();
        this.validated=false;
        this.requireCertification=true;
        this.certificate=certificate;
        this.data=command.data();
    }

    public Evidence(CreateEvidenceCommand command, Type type) {
        this.employeeId= command.employeeId();
        this.type=type;
        this.description=command.description();
        this.validated=false;
        this.requireCertification=false;
        this.certificate=null;
    }

    public Evidence() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isValidated() {
        return validated;
    }

    public boolean isRequireCertification() {
        return requireCertification;
    }

    public String getData() {
        return data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
