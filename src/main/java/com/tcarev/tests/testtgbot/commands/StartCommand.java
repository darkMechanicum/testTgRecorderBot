package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.processors.MessageProcessor;
import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Start command. Enables recording for chat, where command was received.
 */
@Component
public class StartCommand extends BaseCommand {

    /**
     * Message to send when start command is invoked.
     */
    private final String commandMessage;

    @Autowired
    private MessageProcessor messageProcessor;

    protected StartCommand(@Value("${test-tg-bot.start-command.code}") String commandCode,
                           @Value("${test-tg-bot.start-command.description}") String commandDesc,
                           @Value("${test-tg-bot.start-command.message}") String commandMessage) {
        super(commandCode, commandDesc);
        this.commandMessage = commandMessage;
    }

    @Override
    public void doAction(Long chatId, StringSender sender) {
        messageProcessor.start();
        sender.sendString(commandMessage);
    }
}
