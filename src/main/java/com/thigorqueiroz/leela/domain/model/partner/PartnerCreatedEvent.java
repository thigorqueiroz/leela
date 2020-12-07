package com.thigorqueiroz.leela.domain.model.partner;

import com.thigorqueiroz.leela.domain.model.common.DomainEvent;

import java.util.UUID;

public final class PartnerCreatedEvent implements DomainEvent {
    private final String app;
    private final UUID team;
    private final UUID id;
    private final String partner;

    public PartnerCreatedEvent(UUID team, String partner) {
        this.team = team;
        this.partner = partner;
        this.app = "leela";
        this.id = UUID.randomUUID();
    }

    @Override
    public String getApp() {
        return app;
    }

    public String getPartner() {
        return partner;
    }

    public UUID getTeam() {
        return team;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
