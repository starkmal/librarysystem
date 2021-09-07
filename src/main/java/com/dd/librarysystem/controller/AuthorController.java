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

class AuthorJsonData {
    private int id;
    private String desc;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") int id) {
        Optional<Author> authorData = authorRepository.findById(id);

        return authorData.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> searchAuthorByName(@RequestParam String name) {
        try {
            List<Author> authors = authorRepository.findByNameContaining(name);
            if (authors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorJsonData data) {
        try {
            Author _author = authorRepository.save(new Author(data.getName(),data.getDesc()));
            return new ResponseEntity<>(_author, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") int id, @RequestBody Author author) {
        Optional<Author> authorData = authorRepository.findById(id);

        if (authorData.isPresent()) {
            Author _author = authorData.get();
            _author.setDesc(author.getDesc());
            _author.setName(author.getName());
            _author.setBooks(author.getBooks());
            return new ResponseEntity<>(authorRepository.save(_author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") int id) {
        try {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
