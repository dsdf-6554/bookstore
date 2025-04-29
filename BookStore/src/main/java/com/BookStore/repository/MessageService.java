package com.BookStore.repository;

import com.BookStore.dto.MessageDTO;
import java.util.List;

public interface MessageService {
    void sendMessage(MessageDTO messageDTO);
    List<MessageDTO> getMessagesForUser(Long userId);
}
