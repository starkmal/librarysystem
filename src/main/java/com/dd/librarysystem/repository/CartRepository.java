package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByReaderId(int readerId);
}
