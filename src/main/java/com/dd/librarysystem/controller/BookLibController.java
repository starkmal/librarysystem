package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import com.dd.librarysystem.model.Borrow;
import com.dd.librarysystem.repository.BookLibRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
public class BookLibController {
    @Autowired
    BookLibRepository bookLibRepository;

    @GetMapping("/repo/{id}")
    public ResponseEntity<BookLib> getBookinLib(@PathVariable("id") int id) {
        Optional<BookLib> bookLibData = bookLibRepository.findById(id);
        return bookLibData.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/repo")
    public ResponseEntity<BookLib> getBookinLib(@RequestBody BookLib book) {
        try {
            BookLib _book = bookLibRepository.save(new BookLib(book));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/repo/{id}")
    public ResponseEntity<BookLib> updateBorrow(@PathVariable("id") int id, @RequestBody BookLib book) {
        Optional<BookLib> bookLibData = bookLibRepository.findById(id);

        if (bookLibData.isPresent()) {
            BookLib _book = bookLibData.get();
            _book.setIsbn(book.getIsbn());
            _book.setLocation(book.getLocation());
            _book.setState(book.getState());
            return new ResponseEntity<>(bookLibRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/repo/{id}")
    public ResponseEntity<HttpStatus> deleteBorrow(@PathVariable("id") int id) {
        try {
            bookLibRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //与borrow部分逻辑相同
    @PutMapping("/repo/{id}/reserve")
    public ResponseEntity<BookLib> updateReserve(@PathVariable("id") int id, @RequestBody BookLib book) {
        Optional<BookLib> bookLibData = bookLibRepository.findById(id);

        if(bookLibData.isPresent()) {
            BookLib _book = bookLibData.get();
            _book.setIsbn(book.getIsbn());
            _book.setLocation(book.getLocation());
            _book.setState(book.getState());
            return new ResponseEntity<>(bookLibRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
