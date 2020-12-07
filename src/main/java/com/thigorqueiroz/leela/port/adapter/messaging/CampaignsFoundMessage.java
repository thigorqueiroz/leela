package com.thigorqueiroz.leela.port.adapter.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thigorqueiroz.leela.application.command.SubscribeCampaignCommand;

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
    @JsonProperty
    public String partner;

    public CampaignsFoundMessage() {
        super();
    }

    public CampaignsFoundMessage(String app, List<UUID> campaigns, UUID id, String partner) {
        this.app = app;
        this.campaigns = campaigns;
        this.id = id;
        this.partner = partner;
    }

    public SubscribeCampaignCommand toSubscribeCampaigns() {
        return new SubscribeCampaignCommand(campaigns, partner);
    }
}
