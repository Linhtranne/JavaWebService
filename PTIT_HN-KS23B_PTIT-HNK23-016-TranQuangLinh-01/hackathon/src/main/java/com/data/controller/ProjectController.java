package com.data.controller;

import com.data.model.entity.Project;
import com.data.model.response.ApiResponse;
import com.data.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/projects")
public class ProjectController{
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách project thành công", projects, HttpStatus.OK.value()));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Project>> addProject(@RequestBody Project project) {
        if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Tên project đã tồn tại", null, HttpStatus.BAD_REQUEST.value()));
        }
        Project savedProject = projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Thêm project thành công", savedProject, HttpStatus.CREATED.value()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> updateProject(@PathVariable int id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật project thành công", updatedProject, HttpStatus.OK.value()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa Project thành công", null, HttpStatus.OK.value()));
    }

}
