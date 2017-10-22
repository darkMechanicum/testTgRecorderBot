package com.tcarev.tests.testtgbot.processors;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Base class to interact with processed message.
 */
public abstract class CommonProcessedMessage implements Externalizable {

    /**
     * Message chat identifier.
     */
    private long chatId;

    /**
     * Message data. Can be plain text or of
     * photo, video, sticker, document or audio identifier.
     */
    private String data;

    public CommonProcessedMessage(long chatId, String data) {
        this.chatId = chatId;
        this.data = data;
    }

    /**
     * Prepare message to send.
     */
    public abstract void print(DefaultAbsSender sender) throws TelegramApiException;

    public long getChatId() {
        return chatId;
    }

    public String getData() {
        return data;
    }

    @Override
    public final void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(chatId);
        out.writeObject(data);
    }

    @Override
    public final void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.chatId = in.readLong();
        this.data = (String) in.readObject();
    }
}
