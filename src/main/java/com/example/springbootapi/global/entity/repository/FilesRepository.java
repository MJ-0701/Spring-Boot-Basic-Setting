package com.example.springbootapi.global.entity.repository;

import com.example.springbootapi.global.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {
}
