package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class PhotoProcessedMessage extends CommonProcessedMessage {

    public PhotoProcessedMessage() {
        super(0, null);
    }

    public PhotoProcessedMessage(PhotoSize photo, long chatId) {
        super(chatId, photo.getFileId());
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(getData());
        sendPhoto.setChatId(getChatId());
        sender.sendPhoto(sendPhoto);
    }
}
