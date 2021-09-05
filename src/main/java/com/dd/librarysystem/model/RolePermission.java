package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="role_permission")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;


    public RolePermission() {}

    public RolePermission(int id, Role role, Permission permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
