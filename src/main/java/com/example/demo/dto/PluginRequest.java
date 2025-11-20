package com.example.demo.dto;
import lombok.Data;

import java.util.Set;

@Data
public class PluginRequest {
    private String slug;
    private String name;
    private String description;
    private String icon;
    private boolean enabled;
    private Set<String> allowedRoles;  // ["ADMIN", "USER"]
    private String uiConfig;           // JSON string

}

