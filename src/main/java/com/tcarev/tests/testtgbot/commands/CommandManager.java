package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.exceptions.CommandDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Scope("singleton")
public class CommandManager {

    /**
     * Command mapping on their string representation.
     */
    private final ConcurrentMap<String, BaseCommand> commands = new ConcurrentHashMap<>();

    @Autowired @Qualifier("defaultCommand")
    private BaseCommand defaultCommand;

    /**
     * Add command mapping.
     *
     * @throws CommandDuplicateException when code is already used by other command
     */
    void registerCommand(@NotNull BaseCommand command) {
        BaseCommand previous = commands.putIfAbsent(command.getCommandCode(), command);
        if (previous != null) {
            throw new CommandDuplicateException();
        }
    }

    Collection<BaseCommand> getRegistered() {
        return commands.values();
    }

    /**
     * Try to get command by its string code. If no code found, then default command is returned.
     */
    @NotNull
    public BaseCommand getCommand(String stringCode) {
        return commands.getOrDefault(stringCode, defaultCommand);
    }
}
