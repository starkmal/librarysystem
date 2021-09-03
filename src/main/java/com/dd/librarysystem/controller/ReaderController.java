package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.Reader;
import com.dd.librarysystem.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReaderController {
    @Autowired
    ReaderRepository readerRepository;

    @GetMapping("/reader/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable("id") int id) {
        Optional<Reader> readerData = readerRepository.findById(id);

        return readerData.map(reader -> new ResponseEntity<>(reader, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/reader")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        try {
            Reader _reader = readerRepository.save(new Reader(reader));
            return new ResponseEntity<>(_reader, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reader/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable("id") int id, @RequestBody Reader reader) {
        Optional<Reader> readerData = readerRepository.findById(id);
        if (readerData.isPresent()) {
            Reader _reader = readerData.get();
            _reader.setName(reader.getName());
            _reader.setPassword(reader.getPassword());
            _reader.setPhone(reader.getPhone());
            return new ResponseEntity<>(readerRepository.save(_reader), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reader/{id}")
    public ResponseEntity<HttpStatus> deleteReader(@PathVariable("id") int id) {
        try {
            readerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
