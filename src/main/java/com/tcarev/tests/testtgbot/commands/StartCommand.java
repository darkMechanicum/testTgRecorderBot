package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.processors.MessageProcessor;
import com.tcarev.tests.testtgbot.tg.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends BaseCommand {

    @Autowired
    private MessageProcessor messageProcessor;

    protected StartCommand() {
        super("/start", "Command to start recording.");
    }

    @Override
    public void doAction(Long chatId, MessageSender sender) {
        messageProcessor.start();
        sender.sendMessage("Recording started!");
    }
}
