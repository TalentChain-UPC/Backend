package com.identity.identity_service.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateByCompanyResource(
        @NotBlank String name,
        @NotBlank String ruc,
        @NotBlank String sector,
        RegisterEmployeeResource employee
) {
}
