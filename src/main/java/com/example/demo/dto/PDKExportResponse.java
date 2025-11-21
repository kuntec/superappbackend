package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PDKExportResponse {
    private List<PDKExportComponentDTO> components;
    private List<PDKExportActionDTO> actions;
}

