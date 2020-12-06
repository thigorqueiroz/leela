package com.thigorqueiroz.leela.port.adapter.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CampaignsFoundMessage implements Serializable {
    @JsonProperty
    public  String app;
    @JsonProperty
    public List<UUID> campaigns;
    @JsonProperty
    public  UUID id;

    public CampaignsFoundMessage() {
        super();
    }

    public CampaignsFoundMessage(String app, List<UUID> campaigns, UUID id) {
        this.app = app;
        this.campaigns = campaigns;
        this.id = id;
    }
}
