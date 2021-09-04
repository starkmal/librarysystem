package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    List<Reserve> findReserveByBook(Book book);
}

