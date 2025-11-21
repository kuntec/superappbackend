package com.example.demo.dto;

import lombok.Data;

@Data
public class PDKExportAttributeDTO {
    private String attributeName;
    private String type;
    private boolean required;
    private String defaultValue;
    private String options;
    private String validationRules;
}

