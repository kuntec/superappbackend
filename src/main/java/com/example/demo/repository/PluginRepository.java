package com.example.demo.repository;

import com.example.demo.model.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PluginRepository extends JpaRepository<Plugin, Long> {
    // Optionally: Add custom methods like findByName, etc.
}