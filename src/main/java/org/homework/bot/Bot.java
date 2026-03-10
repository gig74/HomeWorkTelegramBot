package org.homework.bot;

import org.homework.api.CommandService;
import org.homework.config.ConfigBotParameter;
import org.homework.logger.Logger;
import org.homework.di.annotations.Register;
import org.homework.di.annotations.Resolve;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Register
public class Bot extends TelegramLongPollingBot {
    @Resolve
    private CommandService commandService;
    @Resolve
    private Logger logger;
    @Resolve
    private ConfigBotParameter configBotParameter;

    @Override
    public String getBotUsername() {
        // Верните имя вашего бота
        return configBotParameter.getBotName(); //"YOUR_BOT_USERNAME";
    }

    @Override
    public String getBotToken() {
        return configBotParameter.getBotToken(); //"YOUR_BOT_TOKEN";
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.debug("Получено новое обновление: " + update.toString());

        // Проверяем, есть ли в обновлении сообщение и текст сообщения
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            if (text.length() >= 1 && text.charAt(0) == '/') {
                String command = text;
                logger.info("Обработка команды: " + command);
                try {
                    if (command.equalsIgnoreCase("/help")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.getHelp(chatId));
                    } else if (command.equalsIgnoreCase("/getInsult")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.getInsult(chatId));
                    } else if (command.equalsIgnoreCase("/getInsultWithTranslate")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.getInsultWithTranslate(chatId));
                    } else if (command.equalsIgnoreCase("/showStatus")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.showStatus(chatId));
                    }else if (command.equalsIgnoreCase("/setDefault")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.setDefault(chatId));
                    }else if (command.equalsIgnoreCase("/setEnRuDictionary")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.setEnRuDictionary(chatId));
                    }else if (command.equalsIgnoreCase("/setRuEnDictionary")) {
                        // Вызов метода getHelp из CommandService
                        execute(commandService.setRuEnDictionary(chatId));
                    }
                    // Отправляем сообщение
                } catch (TelegramApiException e) {
                    logger.error("Ошибка при отправке сообщения в Telegram: " + e.getMessage());
                }
            } else {
                logger.info("Обработка текста: " + text);
                try {
                    execute(commandService.processText(chatId, text));
                    // Отправляем сообщение
                } catch (TelegramApiException e) {
                    logger.error("Ошибка при отправке сообщения в Telegram: " + e.getMessage());
                }
            }
        }
    }
}