package com.BookStore.controller;

import com.BookStore.dto.BookSearchRequestDTO;
import com.BookStore.dto.BookSearchResponseDTO;
import com.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 书籍搜索接口
    @PostMapping("/search")
    public BookSearchResponseDTO searchBooks(@RequestBody BookSearchRequestDTO requestDTO) {
        return bookService.searchBooks(requestDTO);
    }
}
