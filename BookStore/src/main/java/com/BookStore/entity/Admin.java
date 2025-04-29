package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "管理员表") // 映射到数据库中的表名
public class Admin {
    // Getters and Setters
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

    @Column(name = "管理员类型", nullable = false)
    private String adminType;  // 书城管理员、仓库管理员

}