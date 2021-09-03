package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.BookLib;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLibRepository extends JpaRepository<BookLib, Integer> {
    int countByIsbn(String isbn);
}
