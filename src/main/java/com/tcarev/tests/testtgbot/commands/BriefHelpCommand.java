package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Brief command. Prints help for chat, where command was received.
 */
@Component("defaultCommand")
public class BriefHelpCommand extends BaseCommand {

    /**
     * Message pattern to send when briefHelp command is invoked.
     */
    private final String commandMessagePattern;

    @Autowired
    private HelpCommand helpCommand;

    protected BriefHelpCommand(@Value("${test-tg-bot.brief-help-command.code}") String commandCode,
                               @Value("${test-tg-bot.brief-help-command.description}") String commandDesc,
                               @Value("${test-tg-bot.brief-help-command.message-pattern}") String commandMessagePattern) {
        super(commandCode, commandDesc);
        this.commandMessagePattern = commandMessagePattern;
    }

    @Override
    public void doAction(Long chatIt, StringSender sender) {
        sender.sendString(String.format(commandMessagePattern, helpCommand.getCommandCode()));
    }
}
