package com.BookStore.repository;

import com.BookStore.entity.ThirdPartyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyAccountRepository extends JpaRepository<ThirdPartyAccount, Long> {
    ThirdPartyAccount findByAccountNumber(String accountNumber);
}