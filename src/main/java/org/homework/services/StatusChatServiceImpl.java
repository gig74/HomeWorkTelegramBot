package org.homework.services;

import org.homework.api.StatusChatService;
import org.homework.di.annotations.Register;
import org.homework.model.Status;

import java.util.HashMap;
import java.util.Map;

@Register
public class StatusChatServiceImpl implements StatusChatService {

    private Map<String, Status> statusChats = new HashMap<>();

    @Override
    public Status getStatus(String chatId) {
        Status status = statusChats.get(chatId);
        if (status == null)
            status = Status.DEFAULT;
        return status;
    }

    @Override
    public String showStatus(String chatId) {
        Status status = getStatus(chatId);
        return status.getText();
    }

    @Override
    public Boolean setStatus(String chatId, Status status) {
        statusChats.put(chatId, status);
        return true;
    }
}
