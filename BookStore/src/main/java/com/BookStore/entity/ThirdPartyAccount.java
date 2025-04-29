package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "第三方账号表")
public class ThirdPartyAccount {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "绑定ID")
    private Long bindingId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false)
    private User user;

    @Column(name = "第三方类型",nullable = false)
    private String thirdPartyType;

    @Column(name = "第三方ID",nullable = false)
    private String thirdPartyId;

}