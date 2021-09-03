package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name",nullable = false)
    private String name;


    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(Role b) {
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
