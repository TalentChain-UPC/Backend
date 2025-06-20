package com.labor_record.labor_record_service.evidences.application.internal.commandServices;

import com.labor_record.labor_record_service.evidences.domain.model.aggregates.Evidence;
import com.labor_record.labor_record_service.evidences.domain.model.commands.CreateEvidenceCommand;
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

    public EvidencesCommandServiceImpl(
            EvidencesRepository evidencesRepository,
            CertificatesRepository certificatesRepository) {
        this.evidencesRepository = evidencesRepository;
        this.certificatesRepository = certificatesRepository;
    }

    @Override
    public Optional<Evidence> handle(CreateEvidenceCommand command) {
        Type type;
        try{
            type = Type.valueOf(command.type());
        }catch (Exception e){
            return Optional.empty();
        }
        Evidence evidence;
        if(command.createCertificateCommand().employeeId()==0L){
            evidence = new Evidence(command, type);
        }else{
            if(command.createCertificateCommand().url()=="")return Optional.empty();
            // llamar a microservicio identity y ver si commnad.employeeId existe en la DB
            var certificate = new Certificate(command.createCertificateCommand());
            certificatesRepository.save(certificate);
            evidence = new Evidence(command, type, certificate);
        }

        evidencesRepository.save(evidence);
        return Optional.of(evidence);
    }
}
