package com.tcarev.tests.testtgbot.tg;

/**
 * Interface for sending simple text messages
 * without reference to {@code chatId}.
 */
@FunctionalInterface
public interface StringSender {
    /**
     * Send simple {@code String} message to chat.
     */
    void sendString(String messageText);
}
