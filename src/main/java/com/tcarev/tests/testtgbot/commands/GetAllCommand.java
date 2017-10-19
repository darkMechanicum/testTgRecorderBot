package com.tcarev.tests.testtgbot.commands;

import com.tcarev.tests.testtgbot.persistance.ChatMessage;
import com.tcarev.tests.testtgbot.persistance.ChatMessageRepo;
import com.tcarev.tests.testtgbot.tg.MessageSender;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Component
public class GetAllCommand extends BaseCommand {

    protected GetAllCommand() {
        super("/my", "Command for getting all user messages.");
    }

    @Override
    public void doAction(Long chatId, MessageSender sender) {
        ChatMessageRepo messageRepo = getMessageRepo();
        sender.sendMessage("Your messages are: ");
        final int[] messageCount = {1};
        messageRepo
                .findAllByChatId(chatId)
                .forEach(chatMessage -> sender.sendMessage(getMessageLine(messageCount[0]++, chatMessage)));
    }

    private String getMessageLine(int count, ChatMessage chatMessage) {
        return count + ") " + chatMessage.getMessage();
    }
}
