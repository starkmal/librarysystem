package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.Reader;
import com.dd.librarysystem.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class ReaderJsonData {
    private String name;
    private String password;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

@RestController
public class ReaderController {
    @Autowired
    ReaderRepository readerRepository;

    @GetMapping("/reader/search")
    public ResponseEntity<Map<String, Object>> searchBook(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Reader> readers;
            Pageable paging = PageRequest.of(page, size);
            Page<Reader> pageTuts = null;
            if (id != null)
                pageTuts = readerRepository.findById(id, paging);
            else if (name != null)
                pageTuts = readerRepository.findByNameContaining(name, paging);
            assert pageTuts != null;
            readers = pageTuts.getContent();

            Map<String, Object> res = new HashMap<>();
            res.put("readers", readers);
            res.put("currentPage", pageTuts.getNumber());
            res.put("totalItems", pageTuts.getTotalElements());
            res.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reader/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable("id") int id) {
        Optional<Reader> readerData = readerRepository.findById(id);

        return readerData.map(reader -> new ResponseEntity<>(reader, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/reader/count")
    public ResponseEntity<Long> getReaderCount() {
        try {
            return new ResponseEntity<>(readerRepository.count(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reader")
    public ResponseEntity<Reader> createReader(@RequestBody ReaderJsonData data) {
        try {
            Reader _reader = readerRepository.save(new Reader(data.getName(), data.getPassword(), data.getPhone(), 100));
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
            _reader.setCredit(reader.getCredit());
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
