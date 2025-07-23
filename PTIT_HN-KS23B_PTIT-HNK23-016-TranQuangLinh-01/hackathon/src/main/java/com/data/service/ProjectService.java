package com.data.service;

import com.data.model.entity.Project;
import com.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project addProject(Project project) {
        if (projectRepository.existsByProjectName(project.getProjectName())) {
            throw new IllegalArgumentException("Tên project không được để trống");
        }
        return projectRepository.save(project);
    }

    public Project updateProject(int id, Project project) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy project này"));
        if (!existingProject.getProjectName().equals(project.getProjectName()) && projectRepository.existsByProjectName(project.getProjectName())) {
            throw new IllegalArgumentException("Tên project không được để trống");
        }
        existingProject.setProjectName(project.getProjectName());
        existingProject.setTechnology_using(project.getTechnology_using());
        existingProject.setStart_date(project.getStart_date());
        existingProject.setStatus(project.isStatus());
        return projectRepository.save(existingProject);
    }

    public void deleteProject(int id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy project này"));
        project.setStatus(false);
        projectRepository.save(project);
    }
}