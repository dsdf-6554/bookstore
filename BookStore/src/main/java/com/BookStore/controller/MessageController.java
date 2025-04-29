package com.BookStore.controller;

import com.BookStore.dto.MessageDTO;
import com.BookStore.repository.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO messageDTO) {
        messageService.sendMessage(messageDTO);
    }

    @GetMapping("/user/{userId}")
    public List<MessageDTO> getMessagesForUser(@PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }
}
