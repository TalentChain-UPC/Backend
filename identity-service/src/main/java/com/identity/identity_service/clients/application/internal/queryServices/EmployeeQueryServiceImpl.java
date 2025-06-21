package com.identity.identity_service.clients.application.internal.queryServices;

import com.identity.identity_service.clients.domain.model.aggregates.Employee;
import com.identity.identity_service.clients.domain.model.queries.ExistsByEmployeeIdQuery;
import com.identity.identity_service.clients.domain.model.queries.GetEmployeeByIdQuery;
import com.identity.identity_service.clients.domain.services.EmployeeQueryService;
import com.identity.identity_service.clients.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {
    private final EmployeeRepository employeeRepository;

    // OutBoundServices: CompanyValidatorService that will contain:
    /*
        @Service
        public class CompanyValidatorService {

            private final WebClient webClient;

            public CompanyValidatorService(@Value("${company.service.url}") String companyServiceUrl) {
                this.webClient = WebClient.builder()
                        .baseUrl(companyServiceUrl)
                        .build();
            }

            public boolean isCompanyIdValid(Long companyId) {
                try {
                    return webClient.get()
                            .uri("/api/v1/companies/{id}/exists", companyId)
                            .retrieve()
                            .bodyToMono(Boolean.class)
                            .block(); // puedes evitar block() si usas programación reactiva
                } catch (WebClientResponseException.NotFound e) {
                    return false;
                } catch (Exception e) {
                    throw new RuntimeException("Error al verificar companyId", e);
                }
            }
        }
    */

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {

        // obtener el companyId
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Long companyId = auth.getCompanyId;

        /*
            if (!companyValidatorService.isCompanyIdValid(companyId)) {
                throw new IllegalStateException("La empresa no existe");
            }

            return employeeRepository.findByIdAndCompanyId(id, companyId);
        */


        return employeeRepository.findById(query.id());
    }

    @Override
    public Boolean handle(ExistsByEmployeeIdQuery query) {
        return employeeRepository.existsById(query.employeeId());
    }
}
