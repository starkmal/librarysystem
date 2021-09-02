package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Author;
import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") int id) {
        Optional<Author> authorData = authorRepository.findById(id);

        if (authorData.isPresent()) {
            return new ResponseEntity<>(authorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
