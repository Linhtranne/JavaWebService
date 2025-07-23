package com.data.controller;

import com.data.model.dto.ProjectDeployDTO;
import com.data.model.entity.ProjectDeploy;
import com.data.model.response.ApiResponse;
import com.data.service.ProjectDeployService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-deploy")
public class ProjectDeployController {
    private final ProjectDeployService projectDeployService;

    public ProjectDeployController(ProjectDeployService projectDeployService) {
        this.projectDeployService = projectDeployService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDeploy>>> getAllDeployments() {
        List<ProjectDeploy> deployments = projectDeployService.getAllDeployments();
        return ResponseEntity.ok(new ApiResponse<>(true, "Danh sách triển khai dự án", deployments, HttpStatus.OK.value()));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDeploy>> addDeployment(
            @Valid @ModelAttribute ProjectDeployDTO projectDeployDTO) {
        ProjectDeploy savedDeployment = projectDeployService.addDeployment(projectDeployDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Dự án được thêm thành công", savedDeployment, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDeploy>> updateDeployment(
            @PathVariable int id,
            @Valid @ModelAttribute ProjectDeployDTO projectDeployDTO) {
        ProjectDeploy updatedDeployment = projectDeployService.updateDeployment(id, projectDeployDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dự án được sửa thành công", updatedDeployment, HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDeployment(@PathVariable int id) {
        projectDeployService.markDeploymentAsStopped(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa triển khai dự án thành công", null, HttpStatus.OK.value()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProjectDeploy>>> searchDeployments(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String managerName) {
        List<ProjectDeploy> deployments = projectDeployService.searchDeployments(id, managerName);
        return ResponseEntity.ok(new ApiResponse<>(true, "Kết quả tìm kiếm triển khai dự án", deployments, HttpStatus.OK.value()));
    }
}