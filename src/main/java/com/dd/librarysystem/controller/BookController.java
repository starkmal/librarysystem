package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Author;
import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import com.dd.librarysystem.repository.AuthorRepository;
import com.dd.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchBook(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String authorname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Book> books;
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageTuts = null;
            if (isbn != null)
                pageTuts = bookRepository.findByIsbn(isbn, paging);
            else if (title != null)
                pageTuts = bookRepository.findByTitleContaining(title, paging);
            else if (authorname != null) {
                List<Author> author = authorRepository.findByName(authorname);
                int id = author.isEmpty() ? -1 : author.get(0).getId();
                pageTuts = bookRepository.findByAid(id, paging);
            }
            assert pageTuts != null;
            books = pageTuts.getContent();

            Map<String, Object> res = new HashMap<>();
            res.put("books", books);
            res.put("currentPage", pageTuts.getNumber());
            res.put("totalItems", pageTuts.getTotalElements());
            res.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        Optional<Book> bookData = bookRepository.findById(isbn);

        return bookData.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book = bookRepository.save(new Book(book));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable("isbn") String isbn, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(isbn);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(book.getTitle());
            _book.setDescription(book.getDescription());
            _book.setYear(book.getYear());
            _book.setPublisher(book.getPublisher());
            _book.setPrice(book.getPrice());
            _book.setAid(book.getAid());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("isbn") String isbn) {
        try {
            bookRepository.deleteById(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
