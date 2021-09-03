package com.dd.librarysystem.controller;
import com.dd.librarysystem.model.Book;
import com.dd.librarysystem.model.Cart;
import com.dd.librarysystem.repository.BookLibRepository;
import com.dd.librarysystem.repository.BookRepository;
import com.dd.librarysystem.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    BookLibRepository bookLibRepository;
    // 特定读者的购物车
    @GetMapping("/carts/{id}/cart")
    public ResponseEntity<List<Cart>> getReaderCart(@RequestParam("id") int reader_id){
        try{
            List<Cart> carts = new ArrayList<Cart>();
            cartRepository.findByReaderId(reader_id).forEach(carts::add);
            if(carts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 某本书在谁的购物车
//    @GetMapping("/carts/{bookId}")
//    public ResponseEntity<Cart> getBookInCart

    // 创建购物车条目
    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            Cart _cart = cartRepository.save(new Cart(cart));
            return new ResponseEntity<>(_cart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //删除条目 or 完成预定
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") int id) {
        try {
            cartRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
