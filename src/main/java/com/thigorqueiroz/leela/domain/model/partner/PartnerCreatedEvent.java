package com.thigorqueiroz.leela.domain.model.partner;

import com.thigorqueiroz.leela.domain.model.common.DomainEvent;

import java.util.UUID;

public final class PartnerCreatedEvent implements DomainEvent {
    private final String app;
    private final UUID team;
    private final UUID id;

    public PartnerCreatedEvent(UUID team) {
        this.team = team;
        this.app = "leela";
        this.id = UUID.randomUUID();
    }

    @Override
    public String getApp() {
        return app;
    }

    public UUID getTeam() {
        return team;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
