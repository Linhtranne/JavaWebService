package com.data.repository;

import com.data.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    boolean existsByProjectName(String projectName); // Match the renamed field
}