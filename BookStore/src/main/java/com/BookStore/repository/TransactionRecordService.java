package com.BookStore.repository;

import com.BookStore.dto.TransactionRecordQueryRequest;
import com.BookStore.dto.TransactionRecordResponse;

import java.util.List;

public interface TransactionRecordService {

    List<TransactionRecordResponse> queryTransactionRecords(TransactionRecordQueryRequest request);
}
