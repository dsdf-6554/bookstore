package com.BookStore.controller;

import com.BookStore.dto.TransactionRecordQueryRequest;
import com.BookStore.dto.TransactionRecordResponse;
import com.BookStore.repository.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionRecordController {

    @Autowired
    private TransactionRecordService transactionRecordService;

    @PostMapping("/query")
    public List<TransactionRecordResponse> queryTransactionRecords(@RequestBody TransactionRecordQueryRequest request) {
        return transactionRecordService.queryTransactionRecords(request);
    }
}
