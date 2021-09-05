package com.dd.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="phone")
    private String phone;

    //for example, like admin
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public User(int id, String name, String password, String phone, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public User() {

    }

    @JsonIgnore
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Set<String> getRoleNames() {
        Set<String> roleNames = new HashSet<>();
        roleNames.add((getRole().getName()));
        return roleNames;
    }

}
