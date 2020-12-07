package com.thigorqueiroz.leela.port.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thigorqueiroz.leela.application.service.PartnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CampaignsFoundListener {

    private static final Logger log = LoggerFactory.getLogger(CampaignsFoundListener.class);
    private final ObjectMapper objectMapper;
    private final PartnerService partnerService;

    public CampaignsFoundListener(ObjectMapper objectMapper, PartnerService service) {
        this.objectMapper = objectMapper;
        this.partnerService = service;
    }

    @RabbitListener(id = "campaignsFoundListener", queues = {"fry.campaign.found"})
    public void onPartnerCreated(String message) {
        log.info("Received a new message on campaignsFoundListener '{}'", message);
        try {
            var campaignsFound = objectMapper.readValue(message, CampaignsFoundMessage.class);
            log.info("campaigns found: '{}'", campaignsFound);
            partnerService.subscribeCampaign(campaignsFound.toSubscribeCampaigns());
        } catch (JsonProcessingException e) {
            log.error("Error during deserialize message '{}', ex: '{}'", message, e);
        }
    }
}
