package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLibRepository extends JpaRepository<BookLib, Integer> {
    Page<BookLib> findByIsbn(String isbn, Pageable pageable);
    Page<BookLib> findByTitleContaining(String title, Pageable pageable);
    int countByIsbn(String isbn);
    int countByTitle(String title);
    List<BookLib> findByIsbn(String isbn);
}
