package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.Reserve;
import com.dd.librarysystem.repository.ReaderRepository;
import com.dd.librarysystem.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class ReserveController {
    @Autowired
    ReserveRepository reserveRepository;
    ReaderRepository readerRepository;

    @GetMapping("/reserve")
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve) {
        try {
            Reserve _reserve = reserveRepository.save(new Reserve(reserve));
            return new ResponseEntity<>(_reserve, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping("/reserve/book")
    public ResponseEntity<List<Reserve>> getReservedRecordByBook(@RequestBody Book book) {
        try {
            List<Reserve> reserves = new ArrayList<>();
            reserves.addAll(reserveRepository.findReserveByBook(book));
            if (reserves.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }

//TODO:是否加入通过预定人查找预定记录。若是，则需在reserve_book中加入reader字段

//   @GetMapping("/reader/{id}/reserve")
//   public ResponseEntity<List<Reserve>> getReserveRecordByReader(@PathVariable("id") int id) {
//       Optional<Reader> readerData = readerRepository.findById(id);
//       if(readerData.isPresent()) {
//           Reader reader = readerData.get();
//           reader.
//       }
//   }

   @DeleteMapping("/reserve/{id}")
    public ResponseEntity<HttpStatus> deleteReserve(@PathVariable("id") int id) {
        try {
            reserveRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
}
