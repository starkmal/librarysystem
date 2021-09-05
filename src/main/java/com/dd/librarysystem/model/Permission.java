package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String description;


    public Permission(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Permission() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
