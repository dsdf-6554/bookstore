package com.BookStore.repository;

import com.BookStore.entity.ThirdPartyAccount;
import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyAccountRepository extends JpaRepository<ThirdPartyAccount, Long> {

    // 根据用户和第三方类型查询（比如查询某用户是否绑定了支付宝、微信等）
    Optional<ThirdPartyAccount> findByUserAndThirdPartyType(User user, String thirdPartyType);

}
