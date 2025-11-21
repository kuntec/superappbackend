package com.example.demo.dto;
import lombok.Data;

@Data
public class PDKActionRequest {
    private String name;
    private String description;
    private String allowedParameters; // JSON array as string

}
