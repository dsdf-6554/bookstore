package com.BookStore.repository;

import com.BookStore.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // 自定义方法：根据用户名查询管理员
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);
}