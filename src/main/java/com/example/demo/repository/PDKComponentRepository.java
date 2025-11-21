package com.example.demo.repository;

import com.example.demo.entities.PDKComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PDKComponentRepository extends JpaRepository<PDKComponent, Long> {
    Optional<PDKComponent> findByName(String name);
}
