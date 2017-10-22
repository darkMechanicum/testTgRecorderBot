package com.tcarev.tests.testtgbot.tg;

import com.tcarev.tests.testtgbot.persistance.ChatMessage;

/**
 * Simple interface to send chat messages.
 */
public interface MessageSender {

    /**
     * Send chat message.
     */
    void sendMessage(ChatMessage message);
}
