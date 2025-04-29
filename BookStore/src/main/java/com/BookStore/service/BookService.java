package com.BookStore.service;

import com.BookStore.dto.BookSearchRequestDTO;
import com.BookStore.dto.BookSearchResponseDTO;
import com.BookStore.entity.Book;
import com.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookSearchResponseDTO searchBooks(BookSearchRequestDTO requestDTO) {
        List<Book> books;

        // 根据请求的条件组合搜索
        if (requestDTO.getTitle() != null && !requestDTO.getTitle().isEmpty()) {
            books = bookRepository.findByTitleContaining(requestDTO.getTitle());
        } else if (requestDTO.getCategory() != null && !requestDTO.getCategory().isEmpty()) {
            books = bookRepository.findByCategory(requestDTO.getCategory());
        } else if (requestDTO.getKeywords() != null && !requestDTO.getKeywords().isEmpty()) {
            // 关键词搜索（多个关键词）
            books = bookRepository.findByTitleContainingOrDescriptionContaining(requestDTO.getKeywords().get(0), requestDTO.getKeywords().get(1)); // 这里只是示例，实际可以使用循环或动态查询
        } else if (requestDTO.getDescription() != null && !requestDTO.getDescription().isEmpty()) {
            books = bookRepository.findByDescriptionContaining(requestDTO.getDescription());
        } else {
            books = bookRepository.findAll(); // 如果没有传任何条件，返回所有书籍
        }

        return new BookSearchResponseDTO(books);
    }
}
