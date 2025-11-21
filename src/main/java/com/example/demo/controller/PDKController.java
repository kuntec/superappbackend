package com.example.demo.controller;
import com.example.demo.dto.PDKActionRequest;
import com.example.demo.dto.PDKComponentAttributeRequest;
import com.example.demo.dto.PDKComponentRequest;
import com.example.demo.dto.PDKExportResponse;
import com.example.demo.entities.PDKAction;
import com.example.demo.entities.PDKComponent;
import com.example.demo.entities.PDKComponentAttribute;
import com.example.demo.services.PDKService;
import com.example.demo.utils.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdk")
@CrossOrigin(origins = "*")

public class PDKController {

    private final PDKService pdkService;

    public PDKController(PDKService pdkService) {
        this.pdkService = pdkService;
    }

    // ===== COMPONENTS =====

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/components")
    public ApiResponse createComponent(@RequestBody PDKComponentRequest request) {
        PDKComponent component = pdkService.createComponent(request);
        return new ApiResponse(true, "Component created", component);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/components")
    public ApiResponse getAllComponents() {
        List<PDKComponent> components = pdkService.getAllComponents();
        return new ApiResponse(true, "Components list", components);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/components/{id}")
    public ApiResponse getComponent(@PathVariable Long id) {
        PDKComponent component = pdkService.getComponent(id);
        return new ApiResponse(true, "Component details", component);
    }

    // ===== ATTRIBUTES =====

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/components/{id}/attributes")
    public ApiResponse addAttribute(@PathVariable Long id,
                                    @RequestBody PDKComponentAttributeRequest request) {
        PDKComponentAttribute attr = pdkService.addAttributeToComponent(id, request);
        return new ApiResponse(true, "Attribute added", attr);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/components/{id}/attributes")
    public ApiResponse getAttributes(@PathVariable Long id) {
        List<PDKComponentAttribute> attrs = pdkService.getAttributesForComponent(id);
        return new ApiResponse(true, "Component attributes", attrs);
    }

    // ===== ACTIONS =====

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actions")
    public ApiResponse createAction(@RequestBody PDKActionRequest request) {
        PDKAction action = pdkService.createAction(request);
        return new ApiResponse(true, "Action created", action);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/actions")
    public ApiResponse getActions() {
        List<PDKAction> actions = pdkService.getAllActions();
        return new ApiResponse(true, "Actions list", actions);
    }

    // ===== EXPORT (Plugin Dev Tool can call this) =====

    @GetMapping("/export")
    public ApiResponse exportPDK() {
        PDKExportResponse export = pdkService.exportPDK();
        return new ApiResponse(true, "PDK export", export);
    }


}
