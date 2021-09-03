package com.example.librarysystem.model;


import javax.persistence.*;

// 为一次加入购物车操作
@Entity
@Table(name = "borrow_cart")
@IdClass(CartId.class)
// 联合主键：book_id, reader_id, submit_id
public class Cart {
    @Id
    @Column(name = "book_id")
    private String book_id;

    @Id
    @Column(name = "reader_id")
    private String reader_id;

    @Id
    @Column(name = "submit_time")
    private String submit_time;

    public Cart(String book_id, String reader_id, String submit_time) {
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.submit_time = submit_time;
    }

    public Cart(Cart cart){
        this.book_id = cart.book_id;
        this.reader_id = cart.reader_id;
        this.submit_time = cart.submit_time;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "book_id='" + book_id + '\'' +
                ", reader_id='" + reader_id + '\'' +
                ", submit_time='" + submit_time + '\'' +
                '}';
    }
}
