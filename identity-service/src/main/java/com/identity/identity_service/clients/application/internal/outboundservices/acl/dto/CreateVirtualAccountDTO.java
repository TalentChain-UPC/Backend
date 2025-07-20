package com.identity.identity_service.clients.application.internal.outboundservices.acl.dto;

public record CreateVirtualAccountDTO(
        Long employeeId,
        String address
) {
}
