package com.sukhbeersingh.projectmanager.project;

import org.springframework.context.ApplicationEvent;

public class ComponentUpdatedEvent extends ApplicationEvent {
    private final Component component;

    public ComponentUpdatedEvent(Object source, Component component) {
        super(source);
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}