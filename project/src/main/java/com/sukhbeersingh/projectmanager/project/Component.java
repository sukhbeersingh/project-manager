package com.sukhbeersingh.projectmanager.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;


@Entity
public class Component {
    @Id
    @GeneratedValue Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ComponentType type;

    @Enumerated(EnumType.STRING)
    private ComponentAvailability availability = ComponentAvailability.AVAILABLE;

    @Enumerated(EnumType.STRING)
    private ComponentProgress progress = ComponentProgress.NOT_STARTED;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComponentType getType() {
      return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public ComponentAvailability getAvailability() {
      return availability;
    }

    public void setAvailability(ComponentAvailability availability) {
        this.availability = availability;
    }

    public ComponentProgress getProgress() {
      return progress;
    }

    public void setProgress(ComponentProgress progress) {
        this.progress = progress;
    }

    public void updateAvailability(int projectCount) {
      if (projectCount > 1) {
          this.availability = ComponentAvailability.CONFLICTED;
      } else if (projectCount == 1) {
          this.availability = ComponentAvailability.IN_PROGRESS;
      } else {
          this.availability = ComponentAvailability.AVAILABLE;
      }
    }
}