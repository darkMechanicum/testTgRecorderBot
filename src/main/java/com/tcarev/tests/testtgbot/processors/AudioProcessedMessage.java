package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.objects.Audio;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class AudioProcessedMessage extends CommonProcessedMessage {

    public AudioProcessedMessage() {
        super(0, null);
    }

    public AudioProcessedMessage(long chatId, Audio audio) {
        super(chatId, audio.getFileId());
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(getChatId());
        sendAudio.setAudio(getData());
        sender.sendAudio(sendAudio);
    }
}
