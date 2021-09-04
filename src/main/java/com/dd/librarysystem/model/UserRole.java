package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole {

    // `user_id` int(10) unsigned NOT NULL,
    // `role_id` int(10) unsigned NOT NULL
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_id",nullable = false)
    private int userId;

    @Column(name="role_id",nullable = false)
    private int roleId;


    public UserRole() {}

    public UserRole(int roleId, int userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public UserRole(UserRole b) {
        this.userId = b.getUserId();
        this.roleId = b.getRoleId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
