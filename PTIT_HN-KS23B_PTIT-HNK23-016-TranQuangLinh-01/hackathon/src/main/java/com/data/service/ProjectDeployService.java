package com.data.service;

import com.cloudinary.utils.ObjectUtils;
import com.data.model.dto.ProjectDeployDTO;
import com.data.model.entity.Project;
import com.data.model.entity.ProjectDeploy;
import com.data.repository.ProjectDeployRepository;
import com.data.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDeployService {
    private final ProjectDeployRepository projectDeployRepository;
    private final FileUploadService fileUploadService;
    private final Cloudinary cloudinary;
    private final ProjectRepository projectRepository;

    public ProjectDeployService(ProjectDeployRepository projectDeployRepository, FileUploadService fileUploadService, Cloudinary cloudinary, ProjectRepository projectRepository) {
        this.projectDeployRepository = projectDeployRepository;
        this.fileUploadService = fileUploadService;
        this.cloudinary = cloudinary;

        this.projectRepository = projectRepository;
    }
    public List<ProjectDeploy> getAllDeployments() {
        return projectDeployRepository.findAll();
    }

    public ProjectDeploy addDeployment(@Valid ProjectDeployDTO projectDeployDTO) {
        Project project = projectRepository.findById(projectDeployDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ProjectDeploy projectDeploy = new ProjectDeploy();
        projectDeploy.setManagerName(projectDeployDTO.getManagerName());
        projectDeploy.setTotalEmployeeJoined(projectDeployDTO.getTotalEmployeeJoined());
        projectDeploy.setProject(project);

        if (projectDeployDTO.getImage() != null && !projectDeployDTO.getImage().isEmpty()) {
            projectDeploy.setImageManager(uploadImage(projectDeployDTO.getImage()));
        }

        return projectDeployRepository.save(projectDeploy);
    }

    public ProjectDeploy updateDeployment(int id, @Valid ProjectDeployDTO projectDeployDTO) {
        ProjectDeploy existingDeployment = projectDeployRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));

        existingDeployment.setManagerName(projectDeployDTO.getManagerName());
        existingDeployment.setTotalEmployeeJoined(projectDeployDTO.getTotalEmployeeJoined());

        if (projectDeployDTO.getImage() != null && !projectDeployDTO.getImage().isEmpty()) {
            existingDeployment.setImageManager(uploadImage(projectDeployDTO.getImage()));
        }

        return projectDeployRepository.save(existingDeployment);
    }

    private String uploadImage(MultipartFile image) {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to Cloudinary", e);
        }
    }

    public void markDeploymentAsStopped(int id) {
        ProjectDeploy deployment = projectDeployRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dự án"));
        deployment.setStatus(false);
        projectDeployRepository.save(deployment);
    }
    public List<ProjectDeploy> searchDeployments(Integer id, String managerName) {
        if (id != null) {
            return projectDeployRepository.findById(id).stream().toList();
        } else if (managerName != null && !managerName.isEmpty()) {
            return projectDeployRepository.findByManagerNameContainingIgnoreCase(managerName);
        }
        return List.of();
    }
}