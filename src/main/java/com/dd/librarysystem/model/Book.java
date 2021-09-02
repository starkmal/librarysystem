package com.dd.librarysystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
    @Id
    private String isbn;

    @Column(name="price")
    private double price;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="publisher")
    private String publisher;

    @Column(name="year")
    private int year;

    @Column(name="aid")
    private int aid;

    @Column(name="popularity")
    private int popularity;

    public Book() {}

    public Book(String isbn, double price, String title, String description, String publisher, int year, int aid, int popularity) {
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.year = year;
        this.aid = aid;
        this.popularity = popularity;
    }

    public Book(Book b) {
        this.isbn = b.getIsbn();
        this.price = b.getPrice();
        this.title = b.getTitle();
        this.description = b.getDescription();
        this.publisher = b.getPublisher();
        this.year = b.getYear();
        this.aid = b.getAid();
        this.popularity = b.getPopularity();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
