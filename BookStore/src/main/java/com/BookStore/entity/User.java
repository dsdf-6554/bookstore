package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "用户表") // 映射到数据库中的表名
public class User {
    // Getters and Setters
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

    @Column(name = "用户状态", nullable = false)
    private String userStatus = "未登录"; // 默认值为未登录

    @Column(name = "账号状态", nullable = false)
    private String accountStatus = "正常"; // 默认正常

}