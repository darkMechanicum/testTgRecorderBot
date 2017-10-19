package com.tcarev.tests.testtgbot.tg;

import com.tcarev.tests.testtgbot.commands.BaseCommand;
import com.tcarev.tests.testtgbot.commands.CommandManager;
import com.tcarev.tests.testtgbot.processors.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class CommandDrivenBot extends TelegramLongPollingBot {

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
            MessageSender messageSendCallback = message -> this.sendMessage(chatId, message);

            if (updateMsg.isCommand()) {
                BaseCommand command = commandManager.getCommand(msgText);
                command.doAction(chatId, messageSendCallback);
            } else {
                messageProcessor.processMessage(chatId, msgText, messageSendCallback);
            }
        }
        update.getMessage().isCommand();

    }

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

    public String getBotUsername() {
        return "tcarevTestBot";
    }

    public String getBotToken() {
        return "407978013:AAE7Ll26dVbj8goSK5_-qt-f2xNe9eBUVr4";
    }

}
