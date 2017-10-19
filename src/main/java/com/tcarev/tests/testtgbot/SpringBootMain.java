package com.tcarev.tests.testtgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * Spring application entry point.
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootMain {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(SpringBootMain.class, args);
    }
}
