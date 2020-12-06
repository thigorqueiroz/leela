package com.thigorqueiroz.leela.port.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CampaignsFoundListener {

    private static final Logger log = LoggerFactory.getLogger(CampaignsFoundListener.class);
    private final ObjectMapper objectMapper;

    public CampaignsFoundListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(id = "campaignsFoundListener", queues = {"fry.campaign.found"})
    public void onPartnerCreated(String message) {
        log.info("Received a new message on campaignsFoundListener '{}'", message);
        try {
            var campaignsFound = objectMapper.readValue(message, CampaignsFoundMessage.class);
            log.info("campaigns found: '{}'", campaignsFound);
        } catch (JsonProcessingException e) {
            log.error("Error during deserialize message '{}', ex: '{}'", message, e);
        }
    }

}
