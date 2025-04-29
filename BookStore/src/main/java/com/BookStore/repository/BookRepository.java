package com.BookStore.repository;

import com.BookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title); // 根据书名进行模糊搜索
    List<Book> findByCategory(String category); // 根据分类查找
    List<Book> findByDescriptionContaining(String description); // 根据简介内容进行模糊搜索
    List<Book> findByTitleContainingOrDescriptionContaining(String title, String description); // 根据书名和简介内容进行模糊搜索

}
