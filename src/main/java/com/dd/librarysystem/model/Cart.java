package com.dd.librarysystem.model;

import javax.persistence.*;

// 为一次加入购物车操作
@Entity
@Table(name = "borrow_cart")
@IdClass(CartId.class)
// 联合主键：book_id, reader_id, submit_id
public class Cart {
    @Id
    @Column(name = "book_id")
    private Integer bookId;

    @Id
    @Column(name = "reader_id")
    private Integer readerId;

    @Id
    @Column(name = "submit_time")
    private String submitTime;

    public Cart() {
    }

    public Cart(Integer book_id, Integer reader_id, String submit_time) {
        this.bookId = book_id;
        this.readerId = reader_id;
        this.submitTime = submit_time;
    }

    public Cart(Cart cart){
        this.bookId = cart.bookId;
        this.readerId = cart.readerId;
        this.submitTime = cart.submitTime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "book_id='" + bookId + '\'' +
                ", reader_id='" + readerId + '\'' +
                ", submit_time='" + submitTime + '\'' +
                '}';
    }
}
