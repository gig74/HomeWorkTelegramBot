package org.homework.services;

import org.homework.api.CommandService;
import org.homework.api.DataService;
import org.homework.api.StatusChatService;
import org.homework.di.annotations.Register;
import org.homework.di.annotations.Resolve;
import org.homework.logger.Logger;
import org.homework.model.Status;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Register
public class CommandServiceImpl implements CommandService {
    @Resolve
    private DataService dataService;

    @Resolve
    private Logger logger;

    @Resolve
    private StatusChatService statusChatService;

    @Override
    public SendMessage getHelp(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Commands:\n/help - выдача этого текста,\n/showStatus - показать текущий режим,\n/setDefault - режим 'Эхо' (без перевода)" +
                "\n/setEnRuDictionary - режим англо-русского словаря,\n/setRuEnDictionary - режим русско-английского словаря,\n/getInsult - случайное ругательство(англ), " +
                "\n/getInsultWithTranslate - случайное ругательство с переводом на русский.");
        return sendMessage;
    }

    @Override
    public SendMessage showStatus(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        Status status = statusChatService.getStatus(chatId);
        sendMessage.setText("Текущий режим: " + status.getText());
        return sendMessage;
    }

    @Override
    public SendMessage setDefault(String chatId) {
        return setStatus(chatId, Status.DEFAULT);
    }

    @Override
    public SendMessage setEnRuDictionary(String chatId) {
        return setStatus(chatId, Status.EN_RU_DICTIONARY);
    }

    @Override
    public SendMessage setRuEnDictionary(String chatId) {
        return setStatus(chatId, Status.RU_EN_DICTIONARY);
    }

    private SendMessage setStatus(String chatId, Status requestStatus) {
        logger.info("Запрошен режим: " + requestStatus.getText() + " для чата " + chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        statusChatService.setStatus(chatId, requestStatus);
        Status status = statusChatService.getStatus(chatId);
        logger.info("Установлен режим: " + status.getText() + " для чата " + chatId);
        sendMessage.setText("Установлен режим: " + status.getText());
        return sendMessage;
    }

    @Override
    public SendMessage getInsult(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        try {
            logger.info("Запрос сгенерированного ругательства: " + chatId);
            sendMessage.setText(dataService.getInsultData().getInsult());
        } catch (Exception error) {
            logger.error("Ошибка при получении сгенерированного ругательства: " + error.getMessage());
            sendMessage.setText("Произошла ошибка при получении данных. Попробуйте позже.");
        }
        return sendMessage;
    }

    @Override
    public SendMessage getInsultWithTranslate(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String insult ;

        try {
            logger.info("Запрос сгенерированного ругательства: " + chatId);
            insult = dataService.getInsultData().getInsult();
        } catch (Exception error) {
            logger.error("Ошибка при получении сгенерированного ругательства: " + error.getMessage());
            sendMessage.setText("Произошла ошибка при получении данных. Попробуйте позже.");
            return sendMessage;
        }
        try {
            logger.info("Перевод сгенерированного ругательства: " + chatId);
            String insultWithTranslate = "EN: " + insult + "\n" + "RU: " +
                    dataService.getStringTranslate(insult,Status.EN_RU_DICTIONARY.getSourceLang(), Status.EN_RU_DICTIONARY.getTargetLang()).getTranslatedText();
            sendMessage.setText(insultWithTranslate);
        } catch (Exception error) {
            logger.error("Ошибка при переводе сгенерированного ругательства: " + error.getMessage());
            sendMessage.setText("Произошла ошибка при переводе. Попробуйте позже.");
        }
        return sendMessage;
    }

    @Override
    public SendMessage processText(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        Status status = statusChatService.getStatus(chatId);
        if (status == Status.DEFAULT) {
            try {
                logger.info("Эхо: " + chatId);
                sendMessage.setText(dataService.getEcho(text));
            } catch (Exception error) {
                logger.error("Ошибка Эхо' : " + error.getMessage());
                sendMessage.setText("Произошла ошибка при Эхо. Попробуйте позже.");
            }
        } else if (status == Status.EN_RU_DICTIONARY || status == Status.RU_EN_DICTIONARY) {
            try {
                logger.info("Перевод текста с " + status.getSourceLang() + " на " + status.getTargetLang() + " : " + chatId);
                sendMessage.setText(dataService.getStringTranslate(text,status.getSourceLang(),status.getTargetLang()).getTranslatedText());
            } catch (Exception error) {
                logger.error("Ошибка при переводе текста с " + status.getSourceLang() + " на " + status.getTargetLang() + " : " + error.getMessage());
                sendMessage.setText("Произошла ошибка при переводе. Попробуйте позже.");
            }
        } else {
            logger.info("ОШИБКА: Неизвестный статус: " + chatId);
            sendMessage.setText(dataService.getEcho("ОШИБКА: Неизвестный статус "));
        }
        return sendMessage;
    }
}
