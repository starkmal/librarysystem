package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Borrow;
import com.dd.librarysystem.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BorrowController {
    @Autowired
    BorrowRepository borrowRepository;

    @GetMapping("/reader/{id}/borrowrecords")
    public ResponseEntity<List<Borrow>> getReaderBorrowRecords(@PathVariable("id") int reader_id) {
        try {
            List<Borrow> borrows = new ArrayList<Borrow>();
            borrows.addAll(borrowRepository.findByReaderId(reader_id));
            if (borrows.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(borrows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow borrow) {
        try {
            Borrow _borrow = borrowRepository.save(new Borrow(borrow));
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
            _borrow.setReturnLibrarianId(borrow.getReturnLibrarianId());
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
