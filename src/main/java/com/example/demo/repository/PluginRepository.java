package com.example.demo.repository;

import com.example.demo.entities.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PluginRepository extends JpaRepository<Plugin, Long> {
    // Optionally: Add custom methods like findByName, etc.
    Optional<Plugin> findBySlug(String slug);

}