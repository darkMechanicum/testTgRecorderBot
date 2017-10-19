package com.tcarev.tests.testtgbot.processors;

import com.tcarev.tests.testtgbot.persistance.ChatMessage;
import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MessageProcessor {

    @Autowired
    private ChatMessageRepo messageRepo;

    /**
     * Started flag.
     */
    private boolean isStarted = false;

    public void processMessage(Long chatId, String message, MessageSender messageSender) {
        if (!isStarted) {
            return;
        }
        messageSender.sendMessage("Saving message: " + message);
        messageRepo.save(new ChatMessage(message, chatId));
    }

    public void start() {
        isStarted = true;
    }

}
