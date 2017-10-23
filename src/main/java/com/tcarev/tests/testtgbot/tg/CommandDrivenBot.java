package com.tcarev.tests.testtgbot.tg;

import com.tcarev.tests.testtgbot.commands.BaseCommand;
import com.tcarev.tests.testtgbot.commands.CommandManager;
import com.tcarev.tests.testtgbot.persistance.PersistedChatMessage;
import com.tcarev.tests.testtgbot.processors.CommonProcessedMessage;
import com.tcarev.tests.testtgbot.processors.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Simple {@link TelegramLongPollingBot}implementation. Is responsible for
 * message receiving, invoking commands and command processor, and message sending.
 */
@Component
@Scope("singleton")
public class CommandDrivenBot extends TelegramLongPollingBot implements MessageSender {

    @Value("${test-tg-bot.bot-token}")
    private String botToken;
    
    @Value("${test-tg-bot.bot-name}")
    private String botName;
    
    @Autowired
    private CommandManager commandManager;

    /**
     * Function to process regular messages.
     */
    @Autowired
    private MessageProcessor messageProcessor;

    /**
     * Main update processor.
     */
    public void onUpdateReceived(Update update) {
        boolean hasMessage = update.hasMessage();
        if (hasMessage) {
            Message updateMsg = update.getMessage();
            String msgText = updateMsg.getText();
            final Long chatId = updateMsg.getChatId();

            // Callback tied to chat id.
            StringSender messageSendCallback = message -> this.sendMessage(chatId, message);

            if (updateMsg.isCommand()) {
                BaseCommand command = commandManager.getCommand(msgText);
                command.doAction(chatId, messageSendCallback);
            } else {
                messageProcessor.processMessage(updateMsg, messageSendCallback);
            }
        }
        update.getMessage().isCommand();

    }

    /**
     * Send string chat message.
     */
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatId)
                .setText(message);
        try {
            sendMessage(sendMessage); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(PersistedChatMessage message) {
        byte[] messageData = message.getMessageData();
        try {
            ObjectInputStream objectInput = new ObjectInputStream(new ByteArrayInputStream(messageData));
            CommonProcessedMessage processedMessage = (CommonProcessedMessage) objectInput.readObject();
            processedMessage.print(this);
        } catch (IOException | ClassNotFoundException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void initSelf() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            System.out.println("Registering test bot");
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public final String getBotUsername() {
        return botName;
    }

    public final String getBotToken() {
        return botToken;
    }
}
