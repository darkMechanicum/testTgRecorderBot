package com.tcarev.tests.testtgbot.tg;

import com.tcarev.tests.testtgbot.persistance.PersistedChatMessage;

/**
 * Simple interface to send chat messages.
 */
public interface MessageSender {

    /**
     * Send chat message.
     */
    void sendMessage(PersistedChatMessage message);
}
