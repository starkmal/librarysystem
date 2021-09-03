package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name",nullable = false)
    private String name;


    public Permission() {}

    public Permission(String name) {
        this.name = name;
    }

    public Permission(Permission b) {
        this.name = b.getName();
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
}
