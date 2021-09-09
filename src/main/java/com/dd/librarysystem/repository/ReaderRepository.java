package com.dd.librarysystem.repository;

import com.dd.librarysystem.model.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Page<Reader> findById(int id, Pageable pageable);
    Page<Reader> findByNameContaining(String name, Pageable pageable);
    Page<Reader> findByPhoneContaining(String phone, Pageable pageable);
}
