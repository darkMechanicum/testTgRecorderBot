package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.Video;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class VideoProcessedMessage extends CommonProcessedMessage {

    public VideoProcessedMessage() {
        super(0, null);
    }

    public VideoProcessedMessage(long chatId, Video video) {
        super(chatId, video.getFileId());
    }

    @Override
    public void print(DefaultAbsSender sender) throws TelegramApiException {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(getChatId());
        sendVideo.setVideo(getData());
        sender.sendVideo(sendVideo);
    }
}
