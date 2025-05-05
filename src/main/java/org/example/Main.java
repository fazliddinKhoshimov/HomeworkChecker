package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            // Telegram API bilan aloqa o‘rnatamiz
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Bot sinfimizni ro‘yxatdan o‘tkazamiz
            botsApi.registerBot(new MyBot());

            System.out.println("🤖 Bot ishga tushdi!");

        } catch (TelegramApiException e) {
            System.out.println("❌ Botni ishga tushirishda xatolik yuz berdi: " + e.getMessage());
        }
    }
}
