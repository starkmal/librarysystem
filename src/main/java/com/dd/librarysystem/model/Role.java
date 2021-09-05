package com.dd.librarysystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name",nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @OneToMany(mappedBy = "role")
    private List<RolePermission> rolePermissionList;

    public Role(int id, String name, String description, List<User> users, List<RolePermission> rolePermissionList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.rolePermissionList = rolePermissionList;
    }

    public Role() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
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
