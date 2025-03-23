package com.sukhbeersingh.projectmanager.project;

import java.util.Date;
import java.util.Objects;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
  private @Id
  @GeneratedValue Long id;
  private String name;
  private String description;
  private Date start_date;
  private Date end_date;

  @OneToMany
  private List<Component> components;

  @Enumerated(EnumType.STRING)
  private ProjectStatus status = ProjectStatus.NOT_STARTED;

  Project() {}

  Project(String name, Date start_date, Date end_date) {

    this.name = name;
    this.start_date = start_date;
    this.end_date = end_date;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Date getStart_date() {
    return this.start_date;
  }

  public Date getEnd_date() {
    return this.end_date;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
  }

  public List<Component> getComponents() {
    return components;
  }

  public void setComponents(List<Component> components) {
      this.components = components;
      updateStatus();
  }

  public ProjectStatus getStatus() {
      return status;
  }

  public void setStatus(ProjectStatus status) {
      this.status = status;
  }

  public void startProject() {
    this.status = ProjectStatus.IN_PROGRESS;
    for (Component component : components) {
        if (component.getProgress() == ComponentProgress.NOT_STARTED) {
            component.setProgress(ComponentProgress.IN_PROGRESS);
        }
    }
    updateStatus();
  }

  public void updateStatus() {
    boolean allCompleted = true;
    boolean hasConflict = false;
    boolean hasBlocked = false;

    for (Component component : components) {
        if (component.getAvailability() == ComponentAvailability.CONFLICTED) {
            hasConflict = true;
        }
        if (component.getProgress() == ComponentProgress.BLOCKED) {
            hasBlocked = true;
        }
        if (component.getProgress() != ComponentProgress.COMPLETED) {
            allCompleted = false;
        }
    }

    if (hasConflict || hasBlocked) {
        this.status = ProjectStatus.BLOCKED;
    } else if (allCompleted) {
        this.status = ProjectStatus.COMPLETED;
    } else {
        this.status = ProjectStatus.IN_PROGRESS;
    }
  }


  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Project))
      return false;
    Project project = (Project) o;
    return Objects.equals(this.id, project.id) && Objects.equals(this.name, project.name)
        && Objects.equals(this.start_date, project.start_date) 
        && Objects.equals(this.end_date, project.end_date)
        && Objects.equals(this.description, project.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.start_date, this.end_date, this.description);
  }

  @Override
  public String toString() {
    return "Project{" + "id=" + this.id + ", name='" + this.name + '\'' + ", start_date='" + this.start_date + '\'' + ", end_date='" + this.end_date + '\'' +'}' + ", description='" + this.description + '\'' + '}';
  }
}

