package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="book_in_library")
public class BookLib {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="location")
    private String location;

    @Column(name="state")
    private String state;

    public BookLib() {}

    public BookLib(String isbn, String location, String state) {
        this.isbn = isbn;
        this.location = location;
        this.state = state;
    }

    public BookLib(BookLib b) {
        this.isbn = b.getIsbn();
        this.location = b.getLocation();
        this.state = b.getState();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
