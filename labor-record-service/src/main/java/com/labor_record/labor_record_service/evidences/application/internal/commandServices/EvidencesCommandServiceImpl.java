package com.labor_record.labor_record_service.evidences.application.internal.commandServices;

import com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl.ExternalProfileService;
import com.labor_record.labor_record_service.evidences.application.internal.outboundServices.acl.ExternalTransactionsService;
import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.domain.model.commands.ValidateEvidenceCommand;
import com.labor_record.labor_record_service.evidences.domain.model.entities.Certificate;
import com.labor_record.labor_record_service.evidences.domain.model.valueObjects.Type;
import com.labor_record.labor_record_service.evidences.domain.services.EvidenceCommandService;
import com.labor_record.labor_record_service.evidences.infrastructure.persistence.jpa.repositories.CertificatesRepository;
import com.labor_record.labor_record_service.evidences.infrastructure.persistence.jpa.repositories.EvidencesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvidencesCommandServiceImpl implements EvidenceCommandService {
    private final EvidencesRepository evidencesRepository;
    private final CertificatesRepository certificatesRepository;
    private final ExternalProfileService externalProfileService;
    private final ExternalTransactionsService externalTransactionsService;

    public EvidencesCommandServiceImpl(
            EvidencesRepository evidencesRepository,
            CertificatesRepository certificatesRepository,
            ExternalProfileService externalProfileService,
            ExternalTransactionsService externalTransactionsService) {
        this.evidencesRepository = evidencesRepository;
        this.certificatesRepository = certificatesRepository;
        this.externalProfileService = externalProfileService;
        this.externalTransactionsService = externalTransactionsService;
    }

    @Override
    public Optional<Evidence> handle(CreateEvidenceCommand command) {
        Type type;
        try {
            type = Type.valueOf(command.type());
        } catch (Exception e) {
            return Optional.empty();
        }

        if (type.equals(Type.CERTIFICATE)) {
            String url = command.createCertificateCommand().url();
            if (url == null || url.isBlank()) {
                return Optional.empty();
            }

            boolean existsEmployee = externalProfileService.verifyIfEmployeeExists(command.employeeId());
            if (!existsEmployee) {
                return Optional.empty();
            }

            Certificate certificate = new Certificate(command.createCertificateCommand());
            certificatesRepository.save(certificate);

            Evidence evidence = new Evidence(command, type, certificate);
            evidencesRepository.save(evidence);
            return Optional.of(evidence);
        }

        Evidence evidence = new Evidence(command, type);
        evidencesRepository.save(evidence);
        return Optional.of(evidence);
    }

    @Override
    public Optional<Evidence> handle(ValidateEvidenceCommand command) {
        var evidence = evidencesRepository.findById(command.id());
        if(evidence.isEmpty()) return Optional.empty();

        // validar con contrato
        //enviar a endpoint tipo de evidencia, employeeId, data (json)
        boolean validated = externalTransactionsService.validateEvidenceWithContract(
                evidence.get().getType().name(),
                evidence.get().getEmployeeId(),
                evidence.get().getData()
        );

        evidence.get().setValidated(command.validate());
        evidencesRepository.save(evidence.get());
        return evidence;
    }
}
