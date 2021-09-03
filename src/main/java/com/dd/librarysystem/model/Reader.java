package com.dd.librarysystem.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name="reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "credit")
    private int credit = 100;

    public Reader() {}

    public Reader(String name, String password, String phone, int credit) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.credit = credit;
    }

    public Reader(Reader b) {
        this.name = b.getName();
        this.password = b.getPassword();
        this.phone = b.getPhone();
        this.credit = b.getCredit();
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
