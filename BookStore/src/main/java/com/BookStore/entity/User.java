package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "用户表") // 映射到数据库中的表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "用户ID") // 映射到数据库中的字段名
    private Long userId;

    @Column(name = "手机号", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "密码", nullable = false)
    private String password;

    @Column(name = "昵称", nullable = false)
    private String nickname;

    @Column(name = "邮箱", unique = true)
    private String email;

    @Column(name = "注册时间", nullable = false, updatable = false)
    private java.util.Date registrationTime;

    @Column(name = "最后登录时间")
    private java.util.Date lastLoginTime;

    @Column(name = "权限等级", nullable = false)
    private String permissionLevel;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public java.util.Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(java.util.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}