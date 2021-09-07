package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import com.dd.librarysystem.model.Borrow;
import com.dd.librarysystem.model.Reader;
import com.dd.librarysystem.repository.BookLibRepository;
import com.dd.librarysystem.repository.BorrowRepository;
import com.dd.librarysystem.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

class BorrowJsonData {
    int reader_id;
    int book_id;
    Date borrow_time;

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }
}

@RestController
public class BorrowController {
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookLibRepository bookLibRepository;

    @Autowired
    ReaderRepository readerRepository;

    @GetMapping("/borrow/search")
    public ResponseEntity<Map<String, Object>> searchBorrow(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String readername,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Borrow> borrows;
            Pageable paging = PageRequest.of(page, size);
            Page<Borrow> pageTuts = null;
            if (isbn != null)
                pageTuts = borrowRepository.findByBookBookIsbn(isbn, paging);
            else if (title != null)
                pageTuts = borrowRepository.findByBookBookTitleContaining(title, paging);
            else if (readername != null)
                pageTuts = borrowRepository.findByReaderNameContaining(readername, paging);
            assert pageTuts != null;
            borrows = pageTuts.getContent();

            Map<String, Object> res = new HashMap<>();
            res.put("borrows", borrows);
            res.put("currentPage", pageTuts.getNumber());
            res.put("totalItems", pageTuts.getTotalElements());
            res.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<Borrow> createBorrow(@RequestBody BorrowJsonData data) {
        try {
            BookLib book = null;
            Optional<BookLib> book1 = bookLibRepository.findById(data.getBook_id());
            if (book1.isPresent()) book = book1.get();
            else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            Reader reader = null;
            Optional<Reader> reader1 = readerRepository.findById(data.getReader_id());
            if (reader1.isPresent()) reader = reader1.get();
            else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            Borrow _borrow = borrowRepository.save(new Borrow(reader, book, data.getBorrow_time()));
            return new ResponseEntity<>(_borrow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/borrow/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable("id") int id, @RequestBody Borrow borrow) {
        Optional<Borrow> borrowData = borrowRepository.findById(id);

        if (borrowData.isPresent()) {
            Borrow _borrow = borrowData.get();
            _borrow.setReturnTime(borrow.getReturnTime());
            return new ResponseEntity<>(borrowRepository.save(_borrow), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrow/{id}")
    public ResponseEntity<HttpStatus> deleteBorrow(@PathVariable("id") int id) {
        try {
            borrowRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
