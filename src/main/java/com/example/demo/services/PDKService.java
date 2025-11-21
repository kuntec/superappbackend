package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.entities.PDKAction;
import com.example.demo.entities.PDKComponent;
import com.example.demo.entities.PDKComponentAttribute;
import com.example.demo.repository.PDKActionRepository;
import com.example.demo.repository.PDKComponentAttributeRepository;
import com.example.demo.repository.PDKComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDKService {
    private final PDKComponentRepository componentRepository;
    private final PDKComponentAttributeRepository attributeRepository;
    private final PDKActionRepository actionRepository;

    public PDKService(PDKComponentRepository componentRepository,
                      PDKComponentAttributeRepository attributeRepository,
                      PDKActionRepository actionRepository) {
        this.componentRepository = componentRepository;
        this.attributeRepository = attributeRepository;
        this.actionRepository = actionRepository;
    }

    // ===== COMPONENTS =====

    public PDKComponent createComponent(PDKComponentRequest request) {
        PDKComponent component = new PDKComponent();
        component.setName(request.getName());
        component.setDescription(request.getDescription());
        component.setCategory(request.getCategory());
        component.setEnabled(request.isEnabled());
        return componentRepository.save(component);
    }

    public List<PDKComponent> getAllComponents() {
        return componentRepository.findAll();
    }

    public PDKComponent getComponent(Long id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));
    }

    // ===== ATTRIBUTES =====

    public PDKComponentAttribute addAttributeToComponent(Long componentId,
                                                         PDKComponentAttributeRequest request) {
        PDKComponent component = getComponent(componentId);

        PDKComponentAttribute attr = new PDKComponentAttribute();
        attr.setAttributeName(request.getAttributeName());
        attr.setType(request.getType());
        attr.setRequired(request.isRequired());
        attr.setDefaultValue(request.getDefaultValue());
        attr.setOptions(request.getOptions());
        attr.setValidationRules(request.getValidationRules());
        attr.setComponent(component);

        PDKComponentAttribute saved = attributeRepository.save(attr);

        component.getAttributes().add(saved);
        //componentRepository.save(component);

        return saved;
    }

    public List<PDKComponentAttribute> getAttributesForComponent(Long componentId) {
        PDKComponent component = getComponent(componentId);
        return List.copyOf(component.getAttributes());
    }

    // ===== ACTIONS =====

    public PDKAction createAction(PDKActionRequest request) {
        PDKAction action = new PDKAction();
        action.setName(request.getName());
        action.setDescription(request.getDescription());
        action.setAllowedParameters(request.getAllowedParameters());
        return actionRepository.save(action);
    }

    public List<PDKAction> getAllActions() {
        return actionRepository.findAll();
    }

    // ===== EXPORT =====

    public PDKExportResponse exportPDK() {
        List<PDKExportComponentDTO> compDTOs = componentRepository.findAll().stream().map(component -> {
            PDKExportComponentDTO dto = new PDKExportComponentDTO();
            dto.setName(component.getName());
            dto.setDescription(component.getDescription());
            dto.setCategory(component.getCategory());
            dto.setEnabled(component.isEnabled());

            List<PDKExportAttributeDTO> attrDTOs = component.getAttributes().stream().map(attr -> {
                PDKExportAttributeDTO a = new PDKExportAttributeDTO();
                a.setAttributeName(attr.getAttributeName());
                a.setType(attr.getType());
                a.setRequired(attr.isRequired());
                a.setDefaultValue(attr.getDefaultValue());
                a.setOptions(attr.getOptions());
                a.setValidationRules(attr.getValidationRules());
                return a;
            }).toList();

            dto.setAttributes(attrDTOs);

            return dto;
        }).toList();

        List<PDKExportActionDTO> actionDTOs = actionRepository.findAll().stream().map(action -> {
            PDKExportActionDTO dto = new PDKExportActionDTO();
            dto.setName(action.getName());
            dto.setDescription(action.getDescription());
            dto.setAllowedParameters(action.getAllowedParameters());
            return dto;
        }).toList();

        return new PDKExportResponse(compDTOs, actionDTOs);
    }


}
