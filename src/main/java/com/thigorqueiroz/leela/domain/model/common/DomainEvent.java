package com.thigorqueiroz.leela.domain.model.common;

import java.io.Serializable;
import java.time.ZonedDateTime;

public interface DomainEvent extends Serializable {
    default ZonedDateTime occuredOn() {
        return ZonedDateTime.now();
    }
}