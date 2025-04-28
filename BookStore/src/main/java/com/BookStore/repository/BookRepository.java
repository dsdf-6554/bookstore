package com.BookStore.repository;

import com.BookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String keyword);
    Optional<Book> findById(Long bookId);
//    @Query("SELECT new com.example.book.dto.BookStats(b.category, COUNT(b), SUM(b.sales)) FROM Book b GROUP BY b.category")
//    <BookStats>
//    List<BookStats> getBookStatistics();
}