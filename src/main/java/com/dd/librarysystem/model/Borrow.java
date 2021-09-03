package com.dd.librarysystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="borrow_item")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="reader_id")
    private int readerId;

    @Column(name="book_id")
    private int bookId;

    @Column(name="borrow_librarian_id")
    private int borrowLibrarianId;

    @Column(name="return_librarian_id")
    private Integer returnLibrarianId;

    @Column(name="borrow_time")
    private Date borrowTime;

    @Column(name="return_time")
    private Date returnTime;

    public Borrow() {}

    public Borrow(int reader_id, int book_id, int borrow_librarian_id, Integer return_librarian_id, Date borrow_time, Date return_time) {
        this.readerId = reader_id;
        this.bookId = book_id;
        this.borrowLibrarianId = borrow_librarian_id;
        this.returnLibrarianId = return_librarian_id;
        this.borrowTime = borrow_time;
        this.returnTime = return_time;
    }

    public Borrow(Borrow b) {
        this.readerId = b.getReaderId();
        this.bookId = b.getBookId();
        this.borrowLibrarianId = b.getBorrowLibrarianId();
        this.returnLibrarianId = b.getReturnLibrarianId();
        this.borrowTime = b.getBorrowTime();
        this.returnTime = b.getReturnTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int reader_id) {
        this.readerId = reader_id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
    }

    public int getBorrowLibrarianId() {
        return borrowLibrarianId;
    }

    public void setBorrowLibrarianId(int borrow_librarian_id) {
        this.borrowLibrarianId = borrow_librarian_id;
    }

    public Integer getReturnLibrarianId() {
        return returnLibrarianId;
    }

    public void setReturnLibrarianId(Integer return_librarian_id) {
        this.returnLibrarianId = return_librarian_id;
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
}
