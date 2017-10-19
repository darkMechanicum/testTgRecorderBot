package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.tg.MessageSender;
import org.springframework.stereotype.Component;

@Component("defaultCommand")
public class HelpCommand extends BaseCommand {

    protected HelpCommand() {
        super("/help", "Command for getting help.");
    }

    @Override
    public void doAction(Long chatId, MessageSender sender) {
        sender.sendMessage("This is a simple bot for registering your messages.");
        sender.sendMessage("Here are available commands:");
        getCommandManager().getRegistered()
                .forEach(command -> sender.sendMessage(command.getCommandCode() + " - " + command.getCommandDescription()));
    }
}
