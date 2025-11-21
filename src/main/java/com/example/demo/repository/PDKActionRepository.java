package com.example.demo.repository;

import com.example.demo.entities.PDKAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PDKActionRepository extends JpaRepository<PDKAction, Long> {
    Optional<PDKAction> findByName(String name);

}
