package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pdk_component_attributes")
public class PDKComponentAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g., "label", "placeholder", "required", "options"
    @Column(nullable = false)
    private String attributeName;

    // "string", "number", "boolean", "enum", "object", "array"
    @Column(nullable = false)
    private String type;

    private boolean required = false;

    @Column(columnDefinition = "LONGTEXT")
    private String defaultValue; // stored as text or JSON

    @Column(columnDefinition = "LONGTEXT")
    private String options; // JSON for enum options, e.g. ["Gold","Platinum"]

    @Column(columnDefinition = "LONGTEXT")
    private String validationRules; // JSON: { "min":0, "max":10, "regex":"..." }

    @ManyToOne
    @JoinColumn(name = "component_id")
    private PDKComponent component;

}
