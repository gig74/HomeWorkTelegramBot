package org.homework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TranslatedDataRequest {
    private String q;
    private String source;
    private String target;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    // Конструктор класса с аннотацией @JsonCreator для десериализации JSON
    @JsonCreator
    public TranslatedDataRequest(
            @JsonProperty("q") String q,
            @JsonProperty("source") String source,
            @JsonProperty("target") String target) {
        this.q = q;
        this.source = source;
        this.target = target;
    }

}
