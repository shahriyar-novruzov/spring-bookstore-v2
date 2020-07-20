package com.developia.bookstore.repository;

import com.developia.bookstore.model.Order;
import com.developia.bookstore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
