//package com.tcarev.tests.testtgbot;
//
//import com.tcarev.tests.testtgbot.commands.BaseCommand;
//import com.tcarev.tests.testtgbot.processors.MessageProcessor;
//import com.tcarev.tests.testtgbot.tg.MessageSender;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.telegram.telegrambots.ApiContextInitializer;
//import org.telegram.telegrambots.TelegramBotsApi;
//import org.telegram.telegrambots.api.methods.send.SendMessage;
//import org.telegram.telegrambots.api.objects.Message;
//import org.telegram.telegrambots.api.objects.Update;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.exceptions.TelegramApiException;
//
//import javax.annotation.PostConstruct;
//
//public class TestBot extends TelegramLongPollingBot {
//
//    /**
//     * Main update processor.
//     */
//    public void onUpdateReceived(Update update) {
//        //no-op
//    }
//
//    public static void main(String[] args) {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try {
//            System.out.println("Registering test bot");
//            telegramBotsApi.registerBot(new TestBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getBotUsername() {
//        return "tcarevTestBot";
//    }
//
//    public String getBotToken() {
//        return "407978013:AAE7Ll26dVbj8goSK5_-qt-f2xNe9eBUVr4";
//    }
//
//}
