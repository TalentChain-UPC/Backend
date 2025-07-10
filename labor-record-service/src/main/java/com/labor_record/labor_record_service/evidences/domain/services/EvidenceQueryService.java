package com.labor_record.labor_record_service.evidences.domain.services;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.domain.model.queries.GetEvidencesByCompanyIdQuery;

import java.util.List;

public interface EvidenceQueryService {
    List<Evidence> getEvidencesByCompanyId(GetEvidencesByCompanyIdQuery query);
}
