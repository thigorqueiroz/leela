package com.thigorqueiroz.leela.port.adapter.event;

import com.thigorqueiroz.leela.domain.model.partner.PartnerCreatedEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RoutingKeys {
   private static Map<Object, String> routingKeys = new HashMap<>();

    public static Map<Object, String> routingKeys () {
        //TODO: Put all shared config in a common place
        routingKeys.put(PartnerCreatedEvent.class, "resource.partner.created");
        //return Collections.unmodifiableMap(routingKeys());
        return routingKeys;
    }
}
