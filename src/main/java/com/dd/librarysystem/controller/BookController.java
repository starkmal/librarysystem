package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Author;
import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import com.dd.librarysystem.repository.AuthorRepository;
import com.dd.librarysystem.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.*;
import java.util.stream.Collectors;

class BookJsonData {
    private String isbn;
    private double price;
    private String title;
    private String description;
    private String publisher;
    private int year;
    private int popularity;
    private int author_id;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/book/search")
    public ResponseEntity<Map<String, Object>> searchBook(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String author,
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
            else if (author != null)
                pageTuts = bookRepository.findByAuthorNameContaining(author, paging);
            assert pageTuts != null;
            books = pageTuts.getContent();
            System.out.println(books);

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

    //获取分数最高的前10个书
    @GetMapping("/book/top")
    public ResponseEntity<List<Book>> getBookTop() {
        try {
            List<Book> books = bookRepository.findAll().
                    stream().sorted(Comparator.comparingInt(Book::getPopularity).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody BookJsonData data) {
        try {
            Author author = null;
            Optional<Author> author1 = authorRepository.findById(data.getAuthor_id());
            if (author1.isPresent()) author = author1.get();
            Book _book = bookRepository.save(new Book(data.getIsbn(),data.getPrice(),data.getTitle(),data.getDescription(),data.getPublisher(),author, data.getYear(), data.getPopularity()));
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
            _book.setAuthor(book.getAuthor());
            _book.setPopularity(book.getPopularity());
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
