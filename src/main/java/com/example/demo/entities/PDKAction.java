package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pdk_actions")

public class PDKAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g., "api_call", "navigate", "open_url"
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // JSON array of allowed parameters, e.g. ["api_id","label","style"]
    @Column(columnDefinition = "LONGTEXT")
    private String allowedParameters;

}
