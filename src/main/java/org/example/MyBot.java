package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "https://t.me/hmwrkckrbot"; // Bot username
    }

    @Override
    public String getBotToken() {
        return "7838687810:AAGNhJBpYSXT5lJ1e3w2mFJtTZkDuLz-Kjw"; // Bot token from BotFather
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message msg = update.getMessage();

            if (msg.hasText() && msg.getText().equals("/start")) {
                sendText(msg.getChatId(), "📦 Bu *Homework Checker Bot – Boldin*! Iltimos, ZIP fayl yuboring. Fayl nomida guruh nomi, modeli va ism familiya bo‘lishi kerak.");
            }

            else if (msg.hasDocument()) {
                Document doc = msg.getDocument();
                String fileName = doc.getFileName();

                if (fileName.toLowerCase().endsWith(".zip")) {
                    if (isValidZipName(fileName)) {
                        sendText(msg.getChatId(), "✅ ZIP fayl nomi to‘g‘ri! Rahmat. ✅");
                    } else {
                        sendText(msg.getChatId(), "⚠️ ZIP fayl nomi noto‘g‘ri! Fayl nomida guruh nomi, modeli va ism familiya bo‘lishi kerak.\nMasalan: `10A_OOP_Fazliddin_Akoo.zip`");
                    }
                } else {
                    sendText(msg.getChatId(), "❌ Bu ZIP fayl emas. Iltimos, `.zip` formatdagi fayl yuboring.");
                }
            }

            else {
                sendText(msg.getChatId(), "🤔 Noma'lum xabar turini oldim. Iltimos, ZIP fayl yuboring yoki /start buyrug‘ini yuboring.");
            }
        }
    }

    private boolean isValidZipName(String fileName) {
        // Oddiy tekshiruv: 3 ta "_" borligini tekshiramiz
        String nameWithoutExt = fileName.replace(".zip", "");
        return nameWithoutExt.split("_").length >= 3;
    }

    private void sendText(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        message.setParseMode("Markdown");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
