package com.tcarev.tests.testtgbot.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class for persisting chat message.
 */
@Entity
public class PersistedChatMessage {

    private @Id @GeneratedValue Long id;

    private long chatId;

    private byte[] messageData;

    public PersistedChatMessage() {
    }

    public PersistedChatMessage(long chatId, byte[] messageData) {
        this.chatId = chatId;
        this.messageData = messageData;
    }

    public byte[] getMessageData() {
        return messageData;
    }

    public long getChatId() {
        return chatId;
    }
}
