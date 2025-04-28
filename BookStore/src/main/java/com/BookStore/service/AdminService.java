package com.BookStore.service;

import com.BookStore.entity.Admin;
import com.BookStore.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> getAdminByUsername(String username) {
        // 直接返回 findByUsername 的结果
        return adminRepository.findByUsername(username);
    }
}