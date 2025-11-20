package com.example.demo.controller;
import com.example.demo.dto.PluginRequest;
import com.example.demo.dto.PluginResponse;
import com.example.demo.dto.UIConfigResponse;
import com.example.demo.entities.Plugin;
import com.example.demo.entities.Role;
import com.example.demo.repository.PluginRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.services.PluginService;
import com.example.demo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/plugins")
@CrossOrigin(origins = "*") // allow frontend calls
public class PluginController {
    private final PluginService pluginService;
    private final PluginRepository pluginRepository;

    public PluginController(PluginService pluginService, PluginRepository pluginRepository) {
        this.pluginService = pluginService;
        this.pluginRepository = pluginRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ApiResponse create(@RequestBody PluginRequest request) {
        Plugin plugin = pluginService.createPlugin(request);
        return new ApiResponse(true, "Plugin created", plugin);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id,
                              @RequestBody PluginRequest request) {
        Plugin plugin = pluginService.updatePlugin(id, request);
        return new ApiResponse(true, "Plugin updated", plugin);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        pluginService.deletePlugin(id);
        return new ApiResponse(true, "Plugin deleted", null);
    }

    @GetMapping("/")
    public ApiResponse getAll() {
        return new ApiResponse(true, "Plugins list", pluginService.getAllPlugins());
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return new ApiResponse(true, "Plugin details", pluginService.getPlugin(id));
    }


    @GetMapping("/my")
    public ApiResponse getMyPlugins(Authentication auth) {

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        Set<Role> userRoles = userDetails.getUser().getRoles();

        List<Plugin> plugins = pluginService.getPluginsForUser(userRoles);

        // Convert to PluginResponse DTO
        List<PluginResponse> response = plugins.stream().map(plugin -> {
            PluginResponse dto = new PluginResponse();
            dto.setSlug(plugin.getSlug());
            dto.setName(plugin.getName());
            dto.setDescription(plugin.getDescription());
            dto.setIcon(plugin.getIcon());
            dto.setEnabled(plugin.isEnabled());
            return dto;
        }).toList();

        return new ApiResponse(true, "User plugins", response);
    }


    @GetMapping("/config/{slug}")
    public ApiResponse getUIConfig(@PathVariable String slug) {

        Plugin plugin = pluginRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Plugin not found"));

        return new ApiResponse(
                true,
                "Plugin UI config",
                new UIConfigResponse(plugin.getSlug(), plugin.getUiConfig())
        );
    }

}
