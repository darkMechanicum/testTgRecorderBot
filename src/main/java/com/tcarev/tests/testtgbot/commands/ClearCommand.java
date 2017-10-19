package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearCommand extends BaseCommand {

    @Autowired
    private ChatMessageRepo messageRepo;

    protected ClearCommand() {
        super("/clear", "Clears all recorded messages.");
    }

    @Override
    public void doAction(Long chatId, MessageSender sender) {
        messageRepo.deleteByChatId(chatId);
        sender.sendMessage("Cleared all chat records.");
    }
}
