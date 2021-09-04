package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name = "reserve_books")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reserve_id")
    private Integer reserveId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    //@JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;


    public Reserve() {
    }

    public Reserve(Integer reserveId, Book book, Cart cart) {
        this.reserveId = reserveId;
        this.book = book;
        this.cart = cart;
    }

    public Reserve(Reserve reserve) {
        this.reserveId = reserve.reserveId;
        this.book = reserve.book;
        this.cart = reserve.cart;
    }

    public Integer getReserveId() {
        return reserveId;
    }

    public void setReserveId(Integer reserveId) {
        this.reserveId = reserveId;
    }

    public Book getBookId() {
        return book;
    }

    public void setBookId(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "reserveId=" + reserveId +
                ", bookId='" + book + '\'' +
                ", cart=" + cart +
                '}';
    }
}
