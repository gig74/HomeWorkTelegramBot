package org.homework.model;

public enum Status {

    DEFAULT("Режим 'ЭХО'",null,null),
    EN_RU_DICTIONARY("Англо-Русский словарь", "en", "ru"),
    RU_EN_DICTIONARY("Русско-английский словарь", "ru", "en");

    private final String text;
    private final String sourceLang;
    private final String targetLang;

    Status(String text, String sourceLang, String targetLang) {
        this.text = text;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

    public String getText() {
        return text;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }
}
