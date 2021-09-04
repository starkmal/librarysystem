package com.dd.librarysystem.model;

import javax.persistence.*;

@Entity
@Table(name="role_permission")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="role_id",nullable = false)
    private int roleId;

    @Column(name="permission_id",nullable = false)
    private int permissionId;


    public RolePermission() {}

    public RolePermission(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RolePermission(RolePermission b) {
        this.permissionId = b.getPermissionId();
        this.roleId = getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
