package com.example.demo.controller;
import com.example.demo.model.Plugin;
import com.example.demo.repository.PluginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plugins")
@CrossOrigin(origins = "*") // allow frontend calls
public class PluginController {

    @Autowired
    private PluginRepository pluginRepository;

    @GetMapping
    public List<Plugin> getAllPlugins() {
        return pluginRepository.findAll();
    }

    @PostMapping
    public Plugin createPlugin(@RequestBody Plugin plugin) {
        return pluginRepository.save(plugin);
    }

    @GetMapping("/{id}")
    public Plugin getPluginById(@PathVariable Long id) {
        return pluginRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Plugin updatePlugin(@PathVariable Long id, @RequestBody Plugin pluginDetails) {
        Plugin plugin = pluginRepository.findById(id).orElse(null);
        if (plugin != null) {
            plugin.setName(pluginDetails.getName());
            plugin.setDescription(pluginDetails.getDescription());
            plugin.setEnabled(pluginDetails.isEnabled());
            return pluginRepository.save(plugin);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePlugin(@PathVariable Long id) {
        pluginRepository.deleteById(id);
    }
}
