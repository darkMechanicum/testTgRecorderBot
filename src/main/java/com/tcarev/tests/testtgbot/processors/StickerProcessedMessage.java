package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendSticker;
import org.telegram.telegrambots.api.objects.stickers.Sticker;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class StickerProcessedMessage extends CommonProcessedMessage {

    public StickerProcessedMessage() {
        super(0, null);
    }

    public StickerProcessedMessage(long chatId, Sticker sticker) {
        super(chatId, sticker.getFileId());
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setSticker(getData());
        sendSticker.setChatId(getChatId());
        sender.sendSticker(sendSticker);
    }
}
