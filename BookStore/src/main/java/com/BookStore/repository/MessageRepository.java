package com.BookStore.repository;

import com.BookStore.entity.Message;
import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser(User user);
    List<Message> findBySender(User sender);
    void deleteById(Long messageId);
}