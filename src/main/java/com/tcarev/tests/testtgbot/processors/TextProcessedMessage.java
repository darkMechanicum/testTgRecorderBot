package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TextProcessedMessage extends CommonProcessedMessage {

    public TextProcessedMessage() {
        super(0, null);
    }

    public TextProcessedMessage(long chatId, String messageText) {
        super(chatId, messageText);
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        sender.sendMessage(new SendMessage(getChatId(), getData()));
    }
}
