package com.example.demo.dto;
import lombok.Data;

@Data
public class PluginResponse {
    private String slug;
    private String name;
    private String description;
    private String icon;
    private boolean enabled;

}
