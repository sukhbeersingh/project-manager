package com.sukhbeersingh.projectmanager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Component getComponentById(@PathVariable Long id) {
        return componentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Component createComponent(@RequestBody Component component) {
        return componentRepository.save(component);
    }

    @PutMapping("/{id}")
    public Component updateComponent(@PathVariable Long id, @RequestBody Component componentDetails) {
        Component component = componentRepository.findById(id).orElse(null);
        if (component == null) {
            return null;
        }
        component.setName(componentDetails.getName());
        component.setDescription(componentDetails.getDescription());
        component.setType(componentDetails.getType());
        component.setAvailability(componentDetails.getAvailability());
        component.setProgress(componentDetails.getProgress());
        Component updatedComponent = componentRepository.save(component);
        eventPublisher.publishEvent(new ComponentUpdatedEvent(this, updatedComponent));
        return componentRepository.save(component);
    }

    @DeleteMapping("/{id}")
    public void deleteComponent(@PathVariable Long id) {
        componentRepository.deleteById(id);
    }
}