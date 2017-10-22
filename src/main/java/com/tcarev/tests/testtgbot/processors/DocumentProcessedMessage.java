package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.objects.Document;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class DocumentProcessedMessage extends CommonProcessedMessage {

    public DocumentProcessedMessage() {
        super(0, null);
    }

    public DocumentProcessedMessage(long chatId, Document document) {
        super(chatId, document.getFileId());
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(getChatId());
        sendDocument.setDocument(getData());
        sender.sendDocument(sendDocument);
    }
}
