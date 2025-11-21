package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PDKExportComponentDTO {
    private String name;
    private String description;
    private String category;
    private boolean enabled;
    private List<PDKExportAttributeDTO> attributes;
}
