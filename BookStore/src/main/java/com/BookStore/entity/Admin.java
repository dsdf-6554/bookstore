package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "管理员表") // 映射到数据库中的表名
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "管理员ID") // 映射到数据库中的字段名
    private Long adminId;

    @Column(name = "用户名", unique = true, nullable = false)
    private String username;

    @Column(name = "密码", nullable = false)
    private String password;

    @Column(name = "邮箱", unique = true)
    private String email;

    @Column(name = "注册时间", nullable = false, updatable = false)
    private java.util.Date registrationTime;

    @Column(name = "权限等级", nullable = false)
    private String permissionLevel;

    // Getters and Setters
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(java.util.Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}