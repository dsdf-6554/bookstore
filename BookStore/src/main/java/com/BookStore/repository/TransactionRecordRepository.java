package com.BookStore.repository;

import com.BookStore.entity.TransactionRecord;
import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

    // 按用户查询所有交易记录
    List<TransactionRecord> findByUser(User user);

    // 按用户和交易类型查询
    List<TransactionRecord> findByUserAndTransactionType(User user, String transactionType);

    // 按用户和交易时间区间查询
    List<TransactionRecord> findByUserAndTransactionTimeBetween(User user, Date startTime, Date endTime);

    // 按用户、交易类型和时间区间查询
    List<TransactionRecord> findByUserAndTransactionTypeAndTransactionTimeBetween(User user, String transactionType, Date startTime, Date endTime);

}
