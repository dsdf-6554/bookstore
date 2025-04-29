package com.BookStore.repository;

import com.BookStore.entity.Feedback;
import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
}
