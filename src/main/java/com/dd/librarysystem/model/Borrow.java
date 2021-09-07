package com.dd.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="borrow_item")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @JsonIgnoreProperties({"records"})
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Reader reader;

    @NotNull
    @OneToOne
    private BookLib book;

    @NotNull
    @Column(name="borrow_time")
    private Date borrowTime;

    @Column(name="return_time")
    private Date returnTime;

    public Borrow() {}

    public Borrow(Reader reader, BookLib book, Date borrow_time) {
        this.reader = reader;
        this.book = book;
        this.borrowTime = borrow_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrow_time) {
        this.borrowTime = borrow_time;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date return_time) {
        this.returnTime = return_time;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public BookLib getBook() {
        return book;
    }

    public void setBook(BookLib book) {
        this.book = book;
    }
}
