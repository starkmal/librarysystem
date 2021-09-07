package com.dd.librarysystem.controller;

import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.BookLib;
import com.dd.librarysystem.model.Borrow;
import com.dd.librarysystem.model.Cart;
import com.dd.librarysystem.repository.BookLibRepository;
import com.dd.librarysystem.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
public class BookLibController {
    @Autowired
    BookLibRepository bookLibRepository;

    @Autowired
    CartRepository cartRepository;

    /**
     * 返回某本书的剩余数量
     * @return 数量
     */
    @GetMapping("/repo/num/{isbn}")
    public ResponseEntity<Integer> getBookRemain(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(bookLibRepository.countByIsbn(isbn), HttpStatus.OK);
    }

    /**
     * 返回某个isbn的所有藏书
     * @return list
     */
    @GetMapping("/repo/book/{isbn}")
    public ResponseEntity<List<BookLib>> getRepoByISBN(@PathVariable("isbn") String isbn) {
        try {
            List<BookLib> books = bookLibRepository.findByIsbn(isbn);
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据cart中的书isbn返回库存列表
     * @param id
     * @return
     */
    @GetMapping("/repo/cart/{id}")
    public ResponseEntity<List<BookLib>> getRepoByList(@PathVariable("id") int id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            // 根据cart中的所有书的isbn从bookInLib里查找，然后返回一个列表
            // 关于这个列表，不知道实现哪一种：
            // 1、每种书随便从库存中选一本
            // 2、每种书返回所有可选条件（先显示可用数量，然后再打开详情？
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/repo/{id}")
    public ResponseEntity<BookLib> getBookInLib(@PathVariable("id") int id) {
        Optional<BookLib> bookLibData = bookLibRepository.findById(id);
        return bookLibData.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/repo")
    public ResponseEntity<BookLib> createBookInLib(@RequestBody BookLib book) {
        try {
            BookLib _book = bookLibRepository.save(new BookLib(book));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/repo/{id}")
    public ResponseEntity<BookLib> updateBookInLib(@PathVariable("id") int id, @RequestBody BookLib book) {
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
    public ResponseEntity<HttpStatus> deleteBookInLib(@PathVariable("id") int id) {
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
