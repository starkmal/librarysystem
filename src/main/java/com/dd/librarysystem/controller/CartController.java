package com.dd.librarysystem.controller;
import com.dd.librarysystem.model.Cart;
import com.dd.librarysystem.model.Reserve;
import com.dd.librarysystem.repository.BookLibRepository;
import com.dd.librarysystem.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    BookLibRepository bookLibRepository;

    @GetMapping("/carts/{id}/cart")
    public ResponseEntity<Cart> getReaderCart(@PathVariable("id") int id) {
        Optional<Cart> cartData = cartRepository.findById(id);
        if(cartData.isPresent()) {
            Cart cart = cartData.get();
            return new ResponseEntity<>(cart,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/cart/{id}/search")
    public ResponseEntity<List<Reserve>> getBookFromCart(@PathVariable("id") int id) {
        Optional<Cart> cartData = cartRepository.findById(id);
        if (cartData.isPresent()) {
            Cart cart = cartData.get();
            return new ResponseEntity<>(cart.getReserves(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }

    // 创建购物车条目
    @PutMapping("/carts/add")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        try {
            Cart _cart = cartRepository.save(new Cart(cart));
            return new ResponseEntity<>(_cart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //删除条目 or 开始借阅
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
