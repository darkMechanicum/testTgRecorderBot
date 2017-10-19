package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.MessageSender;
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
     * Command manager to auto register.
     */
    @Autowired
    private CommandManager commandManager;

    @Autowired
    private ChatMessageRepo messageRepo;

    protected BaseCommand(String commandCode, String commandDescription) {
        this.commandCode = commandCode;
        this.commandDescription = commandDescription;
    }

    /**
     * Run the command logic.
     */
    public abstract void doAction(Long chatIt, MessageSender sender);

    protected final ChatMessageRepo getMessageRepo() {
        return messageRepo;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public CommandManager getCommandManager() {
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
