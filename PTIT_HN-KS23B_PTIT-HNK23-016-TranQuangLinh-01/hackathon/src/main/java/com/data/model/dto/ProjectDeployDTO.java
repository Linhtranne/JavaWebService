package com.data.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProjectDeployDTO {
    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotNull(message = "Total employee joined is required")
    private Integer totalEmployeeJoined;

    private MultipartFile image;

    @NotNull(message = "Project ID is required")
    private Integer projectId;
}