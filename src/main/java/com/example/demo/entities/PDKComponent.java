package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "pdk_components")
@Data
public class PDKComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // "text", "dropdown"
    private String description;
    private String category;        // "input", "layout", "display", "action"
    private boolean enabled = true;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PDKComponentAttribute> attributes = new HashSet<>();

}
