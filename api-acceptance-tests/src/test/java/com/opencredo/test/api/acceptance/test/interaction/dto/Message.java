package com.opencredo.test.api.acceptance.test.interaction.dto;

public class Message {
    public String senderUserName;
    public String recipientUserName;
    public String message;

    public Message (String senderUserName, String recipientUserName, String message) {
        this.senderUserName = senderUserName;
        this.recipientUserName = recipientUserName;
        this.message = message;
    }
}
