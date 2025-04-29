package com.BookStore.repository;

import com.BookStore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Optional<Inventory> findByBookBookId(Long bookId);


    List<Inventory> findAll();
    List<Inventory> findByStockQuantityLessThan(Integer threshold);
}