package com.BookStore.dto;

import com.BookStore.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookSearchResponseDTO {

    // Getter and Setter
    private List<Book> books; // 搜索到的书籍列表

    // Constructor
    public BookSearchResponseDTO(List<Book> books) {
        this.books = books;
    }

}
