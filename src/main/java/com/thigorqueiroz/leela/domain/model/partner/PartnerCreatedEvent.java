package com.thigorqueiroz.leela.domain.model.partner;

import com.thigorqueiroz.leela.domain.model.common.DomainEvent;

import java.util.UUID;

public final class PartnerCreatedEvent implements DomainEvent {
    String email;
    UUID heartTeamId;

    public PartnerCreatedEvent(String email, UUID heartTeamId) {
        this.email = email;
        this.heartTeamId = heartTeamId;
    }
}
