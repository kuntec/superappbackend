package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plugins")
public class Plugin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Example: "credit_card_application"
    @Column(nullable = false, unique = true)
    private String slug;

    private String name;

    private String description;

    private String type;

    private String icon; // URL for app to display

    private boolean enabled = true;

    // Role-based plugin visibility
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "plugin_roles",
            joinColumns = @JoinColumn(name = "plugin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> allowedRoles = new HashSet<>();

    // Module 7 will use this
    @Column(columnDefinition = "LONGTEXT")
    private String uiConfig;


}
