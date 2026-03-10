package org.homework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TranslatedDataResponse {
    private final String translatedText;

    // Конструктор класса с аннотацией @JsonCreator для десериализации JSON
    @JsonCreator
    public TranslatedDataResponse(
            @JsonProperty("translatedText") String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    @Override
    public String toString() {
        return "TranslatedDataResponse{" +
                "translatedText='" + translatedText + '\'' +
                '}';
    }

}
