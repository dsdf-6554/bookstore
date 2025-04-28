package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ThirdPartyAccount")
public class ThirdPartyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bindingId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String thirdPartyType;

    @Column(nullable = false)
    private String thirdPartyId;

    // Getters and Setters
    public Long getBindingId() {
        return bindingId;
    }

    public void setBindingId(Long bindingId) {
        this.bindingId = bindingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(String thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }
}