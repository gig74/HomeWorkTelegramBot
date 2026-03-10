package org.homework.api;

import org.homework.model.Status;

public interface StatusChatService {
    Status getStatus(String chatId);
    String showStatus(String chatId);
    Boolean setStatus(String chatId, Status status);
}
