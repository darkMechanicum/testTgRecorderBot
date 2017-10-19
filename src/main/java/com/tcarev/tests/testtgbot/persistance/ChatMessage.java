package com.tcarev.tests.testtgbot.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class for persisting chat message.
 */
@Entity
public class ChatMessage {

    private @Id @GeneratedValue Long id;

    private String message;

    private Long chatId;

    public ChatMessage() {
    }

    public ChatMessage(String message, Long chatId) {
        this.message = message;
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }
}
