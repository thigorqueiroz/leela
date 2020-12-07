package com.thigorqueiroz.leela.application.command;

import java.util.List;
import java.util.UUID;

public class SubscribeCampaignCommand {
    public final List<UUID> campaigns;
    public final String partnerEmail;

    public SubscribeCampaignCommand(List<UUID> campaigns,  String partnerEmail) {
        this.campaigns = campaigns;
        this.partnerEmail = partnerEmail;
    }
}
