package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.User;
import com.dd.librarysystem.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUsersByName(String username);


}
