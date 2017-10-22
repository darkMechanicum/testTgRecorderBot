package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.tg.StringSender;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Base class for all commands.
 */
public abstract class BaseCommand {

    /**
     * String representation of command.
     */
    private final String commandCode;

    /**
     * Description of the command;
     */
    private final String commandDescription;

    /**
     * Command manager for auto register.
     */
    @Autowired
    private CommandManager commandManager;

    protected BaseCommand(String commandCode, String commandDescription) {
        this.commandCode = commandCode;
        this.commandDescription = commandDescription;
    }

    /**
     * Run the command logic.
     */
    public abstract void doAction(Long chatIt, StringSender sender);

    String getCommandCode() {
        return commandCode;
    }

    String getCommandDescription() {
        return commandDescription;
    }

    CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Register this command in {@link CommandManager}.
     */
    @PostConstruct
    private void selfRegister() {
        commandManager.registerCommand(this);
    }
}
