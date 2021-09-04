package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="phone")
    private String phone;

    public User(String name, String password,String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
    public User(User b) {
        this.name = b.name;
        this.password = b.password;
        this.phone = b.phone;
    }

    public User() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
