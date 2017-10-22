package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Help command. Prints help, where command was received.
 */
@Component()
public class HelpCommand extends BaseCommand {

    /**
     * Message to send when help command is invoked.
     */
    private final String commandMessage;

    protected HelpCommand(@Value("${test-tg-bot.help-command.code}") String commandCode,
                          @Value("${test-tg-bot.help-command.description}") String commandDesc,
                          @Value("${test-tg-bot.help-command.message}") String commandMessage) {
        super(commandCode, commandDesc);
        this.commandMessage = commandMessage;
    }

    @Override
    public void doAction(Long chatId, StringSender sender) {
        sender.sendString(commandMessage);
        getCommandManager().getRegistered()
                .forEach(command -> sender.sendString(command.getCommandCode() + " - " + command.getCommandDescription()));
    }
}
