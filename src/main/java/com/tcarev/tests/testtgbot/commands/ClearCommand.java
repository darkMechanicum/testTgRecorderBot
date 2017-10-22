package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clear command. Clears all records for chat, where command was received.
 */
@Component
public class ClearCommand extends BaseCommand {

    @Autowired
    private ChatMessageRepo messageRepo;

    /**
     * Message to send when clear command is invoked.
     */
    private final String clearCommandMessage;

    protected ClearCommand(@Value("${test-tg-bot.clear-command.code}") String commandCode,
                           @Value("${test-tg-bot.clear-command.description}") String commandDesc,
                           @Value("${test-tg-bot.clear-command.message}") String clearCommandMessage) {
        super(commandCode, commandDesc);
        this.clearCommandMessage = clearCommandMessage;
    }

    @Override
    public void doAction(Long chatId, StringSender sender) {
        messageRepo.deleteByChatId(chatId);
        sender.sendString(clearCommandMessage);
    }
}
