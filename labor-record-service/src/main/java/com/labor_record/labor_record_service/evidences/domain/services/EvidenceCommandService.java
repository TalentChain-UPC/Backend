package com.labor_record.labor_record_service.evidences.domain.services;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.domain.model.commands.ValidateEvidenceCommand;

import java.util.Optional;

public interface EvidenceCommandService {
    Optional<Evidence> handle(CreateEvidenceCommand command);
    Optional<Evidence> handle(ValidateEvidenceCommand command);
}
