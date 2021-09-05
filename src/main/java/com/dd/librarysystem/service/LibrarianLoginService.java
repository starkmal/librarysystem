package com.dd.librarysystem.service;

import com.dd.librarysystem.model.User;

import java.util.Set;

public interface LibrarianLoginService {

    public User getUserByUsername(String username);

    public Set<String> getRoleByUsername(Long id);

    public Set<String> getPermissionByRoleId(Long id);

}
