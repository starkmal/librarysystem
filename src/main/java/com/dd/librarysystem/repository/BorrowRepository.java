package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    Page<Borrow> findByReaderNameContaining(String name, Pageable pageable);
    Page<Borrow> findByBookBookTitleContaining(String name, Pageable pageable);
    Page<Borrow> findByBookBookIsbn(String isbn, Pageable pageable);
    Page<Borrow> findByBookId(int id, Pageable pageable);
    Page<Borrow> findByReaderNameContainingAndReturnTime(String name, Date time, Pageable pageable);
    Page<Borrow> findByBookBookTitleContainingAndReturnTime(String name, Date time, Pageable pageable);
    Page<Borrow> findByBookBookIsbnAndReturnTime(String isbn, Date time, Pageable pageable);
    Page<Borrow> findByBookIdAndReturnTime(int id, Date time, Pageable pageable);
    Page<Borrow> findByReturnTime(Date time, Pageable pageable);
}
