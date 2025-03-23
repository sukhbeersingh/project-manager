package com.sukhbeersingh.projectmanager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;



@RestController
public class ProjectApplicationController {
  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private ComponentRepository componentRepository;

  @GetMapping("/")
  public RedirectView redirectToProjects() {
      return new RedirectView("/projects");
  }
  
  @GetMapping("/projects")
  public List<Project> getProjectList() {
    return projectRepository.findAll();
  }
  @PostMapping("/project")
  public Project addProject(@RequestBody Project project) {
    return projectRepository.save(project);
  }
  @GetMapping("/project/{id}")
  public Project getProjectDescriptionById(@PathVariable Long id) {
      return projectRepository.findById(id).orElse(null);
  }
  @PutMapping("/project/{id}")
  public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
      Project projectToUpdate = projectRepository.findById(id).orElse(null);
      if (projectToUpdate == null) {
          return null;
      }
      projectToUpdate.setName(project.getName());
      projectToUpdate.setDescription(project.getDescription());
      projectToUpdate.setStart_date(project.getStart_date());
      projectToUpdate.setEnd_date(project.getEnd_date());
      project.setComponents(project.getComponents());
      return projectRepository.save(projectToUpdate);
  }
  @DeleteMapping("/project/{id}")
  public void deleteProject(@PathVariable Long id) {
      projectRepository.deleteById(id);
  }

  @PostMapping("/project/{projectId}/component/{componentId}")
    public Project addComponentToProject(@PathVariable Long projectId, @PathVariable Long componentId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Component component = componentRepository.findById(componentId).orElse(null);
        if (project == null || component == null) {
            return null;
        }
        project.getComponents().add(component);
        component.updateAvailability(project.getComponents().size());
        project.updateStatus();
        componentRepository.save(component);
        return projectRepository.save(project);
    }

    @DeleteMapping("/project/{projectId}/component/{componentId}")
    public Project removeComponentFromProject(@PathVariable Long projectId, @PathVariable Long componentId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Component component = componentRepository.findById(componentId).orElse(null);
        if (project == null || component == null) {
            return null;
        }
        project.getComponents().remove(component);
        component.updateAvailability(project.getComponents().size());
        project.updateStatus();
        componentRepository.save(component);
        return projectRepository.save(project);
    }

    @EventListener
    public void handleComponentUpdatedEvent(ComponentUpdatedEvent event) {
        Component updatedComponent = event.getComponent();
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            if (project.getComponents().contains(updatedComponent)) {
                project.updateStatus();
                projectRepository.save(project);
            }
        }
    }
}
