package com.thigorqueiroz.leela.port.adapter.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thigorqueiroz.leela.application.service.PartnerService;
import com.thigorqueiroz.leela.domain.model.common.BusinessException;
import com.thigorqueiroz.leela.domain.model.common.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.UUID;

@Component
public class RabbitMQEventDispatcher {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(RabbitMQEventDispatcher.class);

    @Autowired
    public RabbitMQEventDispatcher(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @TransactionalEventListener
    @Async
    public void dispatch(DomainEvent event)  {
        var routingKey = RoutingKeys.routingKeys().get(event.getClass());
        if(!StringUtils.hasLength(routingKey)) {
         log.error("there isn't routing key to publish event");
        }
        try {
            var eventAsString = objectMapper.writeValueAsString(new RabbitEvent(event));
            rabbitTemplate.convertAndSend(routingKey, eventAsString);
            log.info("Dispatching event to RabbitMQ: routingKey='{}', rabbitEvent='{}'", routingKey, eventAsString);
        } catch (JsonProcessingException e) {
            log.error("Error during serialize event '{}', ex: '{}'", event, e);
        }
    }
}

class RabbitEvent implements Serializable {
    DomainEvent event;
    String app = "partner";
    UUID eventId = UUID.randomUUID();
    RabbitEvent(DomainEvent event) {
        this.event = event;
    }
}
