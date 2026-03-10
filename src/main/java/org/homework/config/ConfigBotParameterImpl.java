package org.homework.config;

import org.homework.di.annotations.Register;

import java.util.Map;

@Register
public class ConfigBotParameterImpl implements ConfigBotParameter {
    private String botName;
    private String botToken;

    public ConfigBotParameterImpl() {
        Map<String, String> env = System.getenv();
        this.botName = env.get("BOT_NAME");
        this.botToken = env.get("BOT_TOKEN");
    }

    @Override
    public String getBotName() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
