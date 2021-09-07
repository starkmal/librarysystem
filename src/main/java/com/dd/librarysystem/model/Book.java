package com.dd.librarysystem.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

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

    @Column(name="popularity")
    private int popularity;

    @JsonIgnoreProperties({"books"})
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @JsonIgnoreProperties({"book"})
    @OneToMany(mappedBy = "book")
    private List<BookLib> repo;

    public Book() {}

    public Book(String isbn, double price, String title, String description, String publisher, Author author, int year, int popularity) {
        this.isbn = isbn;
        this.price = price;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.year = year;
        this.popularity = popularity;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<BookLib> getRepo() {
        return repo;
    }

    public void setRepo(List<BookLib> repo) {
        this.repo = repo;
    }
}
