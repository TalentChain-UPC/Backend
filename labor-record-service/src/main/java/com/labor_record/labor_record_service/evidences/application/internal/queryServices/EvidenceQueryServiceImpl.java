package com.labor_record.labor_record_service.evidences.application.internal.queryServices;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.domain.model.queries.GetEvidencesByCompanyIdQuery;
import com.labor_record.labor_record_service.evidences.domain.services.EvidenceQueryService;
import com.labor_record.labor_record_service.evidences.infrastructure.persistence.jpa.repositories.EvidencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceQueryServiceImpl implements EvidenceQueryService {
    private final EvidencesRepository evidencesRepository;

    public EvidenceQueryServiceImpl(EvidencesRepository evidencesRepository) {
        this.evidencesRepository = evidencesRepository;
    }

    @Override
    public List<Evidence> getEvidencesByCompanyId(GetEvidencesByCompanyIdQuery query) {
        return evidencesRepository.findAllByCompanyId(query.companyId());
    }
}
