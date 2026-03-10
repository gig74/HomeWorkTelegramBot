package org.homework.api;

import org.homework.model.EvilInsultData;
import org.homework.model.TranslatedDataResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DataService {
    EvilInsultData getInsultData() throws IOException, URISyntaxException;
    TranslatedDataResponse getStringTranslate(String string, String sourceLang, String targetLang) throws IOException, URISyntaxException;
    String getEcho(String text);
}
