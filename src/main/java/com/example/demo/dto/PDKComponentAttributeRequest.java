package com.example.demo.dto;
import lombok.Data;

@Data

public class PDKComponentAttributeRequest {
    private String attributeName;
    private String type;           // "string","number","boolean","enum","object","array"
    private boolean required;
    private String defaultValue;
    private String options;        // JSON string if needed
    private String validationRules; // JSON string

}
