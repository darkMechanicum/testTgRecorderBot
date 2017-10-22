package com.tcarev.tests.testtgbot.processors;

import com.tcarev.tests.testtgbot.persistance.PersistedChatMessage;
import com.tcarev.tests.testtgbot.persistance.PersistedChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

import java.io.*;

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
    private PersistedChatMessageRepo messageRepo;

    @Autowired
    private ProcessedMessageFactory messageFactory;

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
     * @param message user message
     * @param stringSender callback to print to chat
     */
    public void processMessage(Message message, StringSender stringSender) {
        if (!isStarted) {
            return;
        }

        if (needPrintProcessMessage) {
            stringSender.sendString(processMessage);
        }

        CommonProcessedMessage processedMessage = messageFactory.createdProcessedMessage(message);
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);
            objectOutputStream.writeObject(processedMessage);
            objectOutputStream.flush();
            byte[] data = byteOut.toByteArray();
            messageRepo.save(new PersistedChatMessage(message.getChatId(), data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        isStarted = true;
    }

}
