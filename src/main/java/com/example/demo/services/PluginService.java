package com.example.demo.services;
import com.example.demo.dto.PluginRequest;
import com.example.demo.entities.Plugin;
import com.example.demo.entities.Role;
import com.example.demo.repository.PluginRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PluginService {

    private final PluginRepository pluginRepository;
    private final RoleRepository roleRepository;

    public PluginService(PluginRepository pluginRepository, RoleRepository roleRepository) {
        this.pluginRepository = pluginRepository;
        this.roleRepository = roleRepository;
    }

    public Plugin createPlugin(PluginRequest request) {
        Plugin plugin = new Plugin();
        mapPluginRequestToEntity(request, plugin);
        return pluginRepository.save(plugin);
    }

    public Plugin updatePlugin(Long id, PluginRequest request) {
        Plugin plugin = pluginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plugin not found"));

        mapPluginRequestToEntity(request, plugin);

        return pluginRepository.save(plugin);
    }

    public void deletePlugin(Long id) {
        pluginRepository.deleteById(id);
    }

    public Plugin getPlugin(Long id) {
        return pluginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plugin not found"));
    }

    public java.util.List<Plugin> getAllPlugins() {
        return pluginRepository.findAll();
    }

    private void mapPluginRequestToEntity(PluginRequest request, Plugin plugin) {
        plugin.setSlug(request.getSlug());
        plugin.setName(request.getName());
        plugin.setDescription(request.getDescription());
        plugin.setIcon(request.getIcon());
        plugin.setEnabled(request.isEnabled());
        plugin.setUiConfig(request.getUiConfig());

        // Convert role names to Role objects
        Set<Role> roles = request.getAllowedRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName))
                .collect(Collectors.toSet());

        plugin.setAllowedRoles(roles);
    }

    public List<Plugin> getPluginsForUser(Set<Role> userRoles) {
        return pluginRepository.findAll().stream()
                .filter(plugin -> plugin.isEnabled())
                .filter(plugin ->
                        plugin.getAllowedRoles().stream()
                                .anyMatch(userRoles::contains)
                )
                .toList();
    }


}
