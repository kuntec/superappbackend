package com.example.demo.dto;
import lombok.Data;
@Data

public class PDKComponentRequest {
    private String name;
    private String description;
    private String category;
    private boolean enabled = true;

}
