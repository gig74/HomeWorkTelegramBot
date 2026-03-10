package org.homework.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homework.logger.Logger;
import org.homework.model.*;
import org.homework.api.DataService;
import org.homework.api.HttpService;
import org.homework.di.annotations.Register;
import org.homework.di.annotations.Resolve;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

// Класс, представляющий полную структуру ответа API
// Использование сервиса для получения данных
@Register
public class DataServiceImpl implements DataService {
    @Resolve
    private Logger logger;
    @Resolve
    private HttpService httpService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EvilInsultData getInsultData() throws IOException, URISyntaxException {
        logger.debug("Запрос сгенерированного ругательства с API");

        String url = "https://evilinsult.com/generate_insult.php?lang=en&type=json";
        String jsonResponse = httpService.sendGetRequest(url, Map.of());

        EvilInsultData response = objectMapper.readValue(jsonResponse, EvilInsultData.class);
        return response;
    }

    @Override
    public TranslatedDataResponse getStringTranslate(String string, String sourceLang, String targetLang) throws IOException, URISyntaxException {
        logger.debug("Перевод c " + sourceLang + " на " + targetLang + " строки: " + string);
        String url = "http://localhost:5000/translate";
        String translatedText = string;
        TranslatedDataRequest translatedDataRequest = new TranslatedDataRequest(translatedText, sourceLang, targetLang);
        String jsonPostString = objectMapper.writeValueAsString(translatedDataRequest);
        String jsonResponse = httpService.sendPostRequest(url, Map.of("Content-Type","application/json; charset=utf-8"), jsonPostString);
        TranslatedDataResponse response = objectMapper.readValue(jsonResponse, TranslatedDataResponse.class);
        return response;
    }

    @Override
    public String getEcho(String text) {
        return ("Эхо: " + text);
    }
}
