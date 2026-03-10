package org.homework.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandService {

    SendMessage getHelp(String chatId);

    SendMessage showStatus(String chatId);

    SendMessage setDefault(String chatId);

    SendMessage setEnRuDictionary(String chatId);

    SendMessage setRuEnDictionary(String chatId);

    SendMessage getInsult(String chatId);

    SendMessage getInsultWithTranslate(String chatId);

    SendMessage processText(String chatId, String text);
}
