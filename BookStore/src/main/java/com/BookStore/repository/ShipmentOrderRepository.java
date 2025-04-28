package com.BookStore.repository;

import com.BookStore.entity.ShipmentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentOrderRepository extends JpaRepository<ShipmentOrder, Long> {
    List<ShipmentOrder> findAll();
}
