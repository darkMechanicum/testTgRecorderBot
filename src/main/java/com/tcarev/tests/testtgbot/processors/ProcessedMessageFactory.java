package com.tcarev.tests.testtgbot.processors;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;

import java.util.List;

@Component
public class ProcessedMessageFactory {

    public CommonProcessedMessage createdProcessedMessage(Message message) {
        Long chatId = message.getChatId();
        if (message.hasText()) {
            return new TextProcessedMessage(chatId, message.getText());
        } else if (message.getSticker() != null) {
            return new StickerProcessedMessage(chatId, message.getSticker());
        } else if (message.getAudio() != null) {
            return new AudioProcessedMessage(chatId, message.getAudio());
        } else if (message.hasPhoto()) {
            List<PhotoSize> photos = message.getPhoto();
            return new PhotoProcessedMessage(photos.get(0), chatId);
        } else if (message.getVideo() != null) {
            return new VideoProcessedMessage(chatId, message.getVideo());
        } else if (message.hasDocument()) {
            return new DocumentProcessedMessage(chatId, message.getDocument());
        } else {
            return null;
        }
    }
}
