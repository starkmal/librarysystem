package com.example.librarysystem.repository;

import com.example.librarysystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByReader_id(int reader_id);
    List<Cart> findByBook_id(String book_id);
}
