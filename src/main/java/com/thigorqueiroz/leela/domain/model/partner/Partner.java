package com.thigorqueiroz.leela.domain.model.partner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.thigorqueiroz.leela.domain.model.common.AggregateRootWithIdentifierAsUUID;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("partner")
@JsonRootName("partner")
public class Partner extends AggregateRootWithIdentifierAsUUID<Partner> {

    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    @JsonProperty("birthday")
    public String birthDay;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    OffsetDateTime updatedAt = OffsetDateTime.now();

    public Partner(){
        super();
    }

    public Partner(String name, String email, String birthDay,UUID heartTeamId) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        registerEvent(new PartnerCreatedEvent(heartTeamId, email));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;

        Partner partner = (Partner) o;

        if (id != null ? !id.equals(partner.id) : partner.id != null) return false;
        if (name != null ? !name.equals(partner.name) : partner.name != null) return false;
        if (email != null ? !email.equals(partner.email) : partner.email != null) return false;
        if (birthDay != null ? !birthDay.equals(partner.birthDay) : partner.birthDay != null) return false;
        if (createdAt != null ? !createdAt.equals(partner.createdAt) : partner.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(partner.updatedAt) : partner.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}