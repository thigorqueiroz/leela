package com.thigorqueiroz.leela.port.adapter.resource.exceptionhandler;

import com.thigorqueiroz.leela.domain.model.common.BusinessException;
import com.thigorqueiroz.leela.domain.model.common.EntityNotFoundException;
import com.thigorqueiroz.leela.domain.model.common.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handle(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                createDefaultResponse(ex, "business.error", ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                createDefaultResponse(ex, "business.error", ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handle(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                createDefaultResponse(ex, "bad.thing.happenned", ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                createDefaultResponse(ex, "io.thing.happenned", ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleIOException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                createDefaultResponse(ex, "spring.thing.happenned", ex.getReason())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleIOException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                createDefaultResponse(ex, "illegal.thing.happenned", ex.getMessage())
        );
    }

    private ExceptionMessage createDefaultResponse(Throwable ex, String key, String message) {
        log.error("An erro occur: '{}' in this class '{}'", ex.getMessage(), ex.getClass());
        return new ExceptionMessage(key, message);
    }

}
