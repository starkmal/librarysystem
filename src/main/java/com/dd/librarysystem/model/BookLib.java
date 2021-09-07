package com.dd.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="book_in_library")
public class BookLib {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="location")
    private String location;

    @Column(name="state")
    private String state;

    @JsonIgnoreProperties({"repo"})
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    public BookLib() {}

    public BookLib(Book book, String location, String state) {
        this.book = book;
        this.location = location;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
