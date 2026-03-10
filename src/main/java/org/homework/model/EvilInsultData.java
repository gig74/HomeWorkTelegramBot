package org.homework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class EvilInsultData {
    private final int number;
    private final String language;
    private final String insult;
    private final String created;
    private final String shown;
    private final String createdby;
    private final String active;
    private final String comment;

    // Конструктор класса с аннотацией @JsonCreator для десериализации JSON
    @JsonCreator
    public EvilInsultData(
            @JsonProperty("number") int number,
            @JsonProperty("language") String language,
            @JsonProperty("insult") String insult,
            @JsonProperty("created") String created,
            @JsonProperty("shown") String shown,
            @JsonProperty("createdby") String createdby,
            @JsonProperty("active") String active,
            @JsonProperty("comment") String comment) {
        this.number = number;
        this.language = language;
        this.insult = insult;
        this.created = created;
        this.shown = shown;
        this.createdby = createdby;
        this.active = active;
        this.comment = comment;
    }

    public String getInsult() {
        return insult;
    }

    @Override
    public String toString() {
        return "EvilInsultData{" +
                "number=" + number +
                ", language='" + language + '\'' +
                ", insult='" + insult + '\'' +
                ", created='" + created + '\'' +
                ", shown='" + shown + '\'' +
                ", createdby='" + createdby + '\'' +
                ", active='" + active + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
