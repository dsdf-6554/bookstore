package com.BookStore.service;

import com.BookStore.dto.TransactionRecordQueryRequest;
import com.BookStore.dto.TransactionRecordResponse;
import com.BookStore.entity.TransactionRecord;
import com.BookStore.entity.User;
import com.BookStore.repository.TransactionRecordRepository;
import com.BookStore.repository.UserRepository;
import com.BookStore.repository.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TransactionRecordResponse> queryTransactionRecords(TransactionRecordQueryRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        List<TransactionRecord> records;

        if (request.getTransactionType() != null && request.getStartTime() != null && request.getEndTime() != null) {
            records = transactionRecordRepository.findByUserAndTransactionTypeAndTransactionTimeBetween(
                    user, request.getTransactionType(), request.getStartTime(), request.getEndTime());
        } else if (request.getTransactionType() != null) {
            records = transactionRecordRepository.findByUserAndTransactionType(user, request.getTransactionType());
        } else if (request.getStartTime() != null && request.getEndTime() != null) {
            records = transactionRecordRepository.findByUserAndTransactionTimeBetween(
                    user, request.getStartTime(), request.getEndTime());
        } else {
            records = transactionRecordRepository.findByUser(user);
        }

        return records.stream().map(record -> {
            TransactionRecordResponse response = new TransactionRecordResponse();
            response.setTransactionRecordId(record.getTransactionRecordId());
            response.setTransactionAmount(record.getTransactionAmount());
            response.setTransactionTime(record.getTransactionTime());
            response.setTransactionType(record.getTransactionType());
            return response;
        }).collect(Collectors.toList());
    }
}
