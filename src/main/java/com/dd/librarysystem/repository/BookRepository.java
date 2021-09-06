package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    Page<Book> findByIsbn(String isbn, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Page<Book> findByAid(int aid, Pageable pageable);
    List<Book> findByAid(int aid);
}
