package com.thigorqueiroz.leela.domain.model.common;

public class ExceptionMessage {
    private String keyMessage;
    private String userMessage;

    public ExceptionMessage(String keyMessage, String userMessage) {
        this.keyMessage = keyMessage;
        this.userMessage = userMessage;
    }

    public String getKeyMessage() {
        return keyMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
