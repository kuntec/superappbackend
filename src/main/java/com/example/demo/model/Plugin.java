package com.example.demo.model;

import jakarta.persistence.*;

@Entity // ✅ This tells JPA to treat it as a database table
@Table(name = "plugins") // ✅ Optional: specify actual table name in MySQL
public class Plugin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean enabled;

    // 🔁 Required: Getters and Setters for ALL fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}