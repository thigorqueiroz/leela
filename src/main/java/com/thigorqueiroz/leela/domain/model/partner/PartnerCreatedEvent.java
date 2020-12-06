package com.thigorqueiroz.leela.domain.model.partner;

import com.thigorqueiroz.leela.domain.model.common.DomainEvent;

import java.util.UUID;

public final class PartnerCreatedEvent implements DomainEvent {
    UUID team;

    public PartnerCreatedEvent(UUID team) {
        this.team = team;
    }
}
