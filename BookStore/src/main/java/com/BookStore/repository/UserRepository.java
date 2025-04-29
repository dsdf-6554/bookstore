package com.BookStore.repository;

import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserStatus(String userStatus);
    void deleteById(Long userId);
}