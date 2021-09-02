package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAid(int aid);
}
