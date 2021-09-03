package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    List<Reader> findByName (String name);
}
