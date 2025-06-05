package com.identity.identity_service.clients.domain.model.aggregates;

import com.identity.identity_service.clients.domain.model.commands.CreateEmployeeCommand;
import com.identity.identity_service.clients.domain.model.valueObjects.Area;
import com.identity.identity_service.clients.domain.model.valueObjects.ContactInfo;
import com.identity.identity_service.clients.domain.model.valueObjects.FullName;
import com.identity.identity_service.clients.domain.model.valueObjects.IdentityInfo;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Employee{
    @Id
    private UUID id;
    @Embedded
    private FullName fullName;
    @Embedded
    private IdentityInfo identity;
    @Embedded
    private ContactInfo contactInfo;

    private String occupation;

    private Area area;

    public Employee() {}

    public Employee(CreateEmployeeCommand command,Area area){
        this.id = UUID.randomUUID();
        this.fullName=new FullName(command.name(),command.lastName());
        this.identity=new IdentityInfo(command.age(),command.dni(),command.gender(),command.location());
        this.contactInfo=new ContactInfo(command.phoneNumber(),command.workEmail(),command.personalEmail());
        this.occupation=command.occupation();
        this.area=area;
    }

}
