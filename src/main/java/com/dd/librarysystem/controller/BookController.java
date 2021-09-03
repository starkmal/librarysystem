package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Author;
import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.repository.AuthorRepository;
import com.dd.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksBy(
            @RequestParam(required = false)
            String title,
            @RequestParam(required = false)
            String isbn,
            @RequestParam(required = false)
            String author
    ) {
        try {
            List<Book> books = new ArrayList<Book>();

            if (isbn != null)
                bookRepository.findAll().forEach(books::add);
            else if (title != null)
                bookRepository.findByTitleContaining(title).forEach(books::add);
            else if (author != null) {
                List<Author> authors = new ArrayList<Author>();
                authorRepository.findByName(author).forEach(authors::add);
                for (Author a: authors) {
                    bookRepository.findByAid(a.getId()).forEach(books::add);
                }
            }

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        Optional<Book> bookData = bookRepository.findById(isbn);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @PutMapping("/book/{id}")
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

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("isbn") String isbn) {
        try {
            bookRepository.deleteById(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
