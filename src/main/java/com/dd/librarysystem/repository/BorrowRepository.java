package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    List<Borrow> findByReaderId(int reader_id);
    List<Borrow> findByBorrowLibrarianId(int borrow_librarian_id);
    List<Borrow> findByReturnLibrarianId(int return_librarian_id);
    List<Borrow> findByBookId(int book_id);
}
