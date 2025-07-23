package com.data.repository;

import com.data.model.entity.ProjectDeploy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDeployRepository extends JpaRepository<ProjectDeploy, Integer> {
    List<ProjectDeploy> findByManagerNameContainingIgnoreCase(String managerName);
}
