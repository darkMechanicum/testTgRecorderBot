package com.tcarev.tests.testtgbot.processors;

import com.tcarev.tests.testtgbot.persistance.ChatMessage;
import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Processes all incoming messages, but not commands.
 */
@Component
@Scope("singleton")
public class MessageProcessor {

    /**
     * Message to send while processing user message.
     */
    private final String processMessage;

    /**
     * Flag to enable printing process message at every user message.
     */
    private final boolean needPrintProcessMessage;

    @Autowired
    private ChatMessageRepo messageRepo;

    /**
     * Started flag.
     */
    private boolean isStarted = false;

    /**
     * Constructor for autowiring values.
     */
    public MessageProcessor(@Value("${test-tg-bot.process-message}") String processMessage,
                            @Value("${test-tg-bot.need-process-message}") boolean needPrintProcessMessage) {
        this.processMessage = processMessage;
        this.needPrintProcessMessage = needPrintProcessMessage;
    }

    /**
     * Process incoming user message.
     *
     * @param chatId chat where message was sent
     * @param message user message text
     * @param stringSender callback to print to chat
     */
    public void processMessage(Long chatId, String message, StringSender stringSender) {
        if (!isStarted) {
            return;
        }
        if (needPrintProcessMessage) {
            stringSender.sendString(processMessage);
        }
        messageRepo.save(new ChatMessage(message, chatId));
    }

    public void start() {
        isStarted = true;
    }

}
