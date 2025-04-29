package com.BookStore.service;

import com.BookStore.dto.MessageDTO;
import com.BookStore.entity.Message;
import com.BookStore.entity.User;
import com.BookStore.repository.MessageRepository;
import com.BookStore.repository.UserRepository;
import com.BookStore.repository.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setSendTime(new Date());
        message.setUser(userRepository.findById(messageDTO.getUserId()).orElseThrow());
        message.setSender(userRepository.findById(messageDTO.getSenderId()).orElseThrow());

        messageRepository.save(message);
    }

    @Override
    public List<MessageDTO> getMessagesForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return messageRepository.findByUser(user).stream().map(msg -> {
            MessageDTO dto = new MessageDTO();
            dto.setMessageId(msg.getMessageId());
            dto.setContent(msg.getContent());
            dto.setSendTime(msg.getSendTime());
            dto.setSenderId(msg.getSender().getUserId());
            dto.setUserId(msg.getUser().getUserId());
            return dto;
        }).collect(Collectors.toList());
    }
}
