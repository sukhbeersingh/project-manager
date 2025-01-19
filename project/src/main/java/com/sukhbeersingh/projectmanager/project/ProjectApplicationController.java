package com.sukhbeersingh.projectmanager.project;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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
      return projectRepository.save(projectToUpdate);
  }
}
