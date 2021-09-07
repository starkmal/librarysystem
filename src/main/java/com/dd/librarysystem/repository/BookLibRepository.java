package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.BookLib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLibRepository extends JpaRepository<BookLib, Integer> {
}
