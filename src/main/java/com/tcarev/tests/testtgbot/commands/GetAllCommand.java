package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.persistance.PersistedChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.MessageSender;
import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Get all records command. Prints all records for chat, where command was received.
 */
@Component
public class GetAllCommand extends BaseCommand {

    @Autowired
    private PersistedChatMessageRepo messageRepo;

    @Autowired
    private MessageSender messageSender;

    /**
     * Prefix when printing user messages.
     */
    private final String userMsgPrefixPattern;

    /**
     * Flag to enable prefix when printing recorded messages.
     */
    private final boolean needPrefix;

    /**
     * Message to send when getAll command is invoked.
     */
    private final String commandMessage;

    protected GetAllCommand(@Value("${test-tg-bot.get-all-command.code}") String commandCode,
                            @Value("${test-tg-bot.get-all-command.description}") String commandDesc,
                            @Value("${test-tg-bot.get-all-command.prefix-pattern}") String userMsgPrefixPattern,
                            @Value("${test-tg-bot.get-all-command.need-prefix}") boolean needPrefix,
                            @Value("${test-tg-bot.get-all-command.message}") String commandMessage) {
        super(commandCode, commandDesc);
        this.userMsgPrefixPattern = userMsgPrefixPattern;
        this.needPrefix = needPrefix;
        this.commandMessage = commandMessage;
    }

    @Override
    public void doAction(Long chatId, StringSender stringSender) {
        stringSender.sendString(commandMessage);
        final int[] messageCount = {1};
        messageRepo
                .findAllByChatId(chatId)
                .forEach(chatMessage -> {
                    if (needPrefix) {
                        stringSender.sendString(String.format(userMsgPrefixPattern, messageCount[0]++));
                    }
                    messageSender.sendMessage(chatMessage);
                });
    }
}
