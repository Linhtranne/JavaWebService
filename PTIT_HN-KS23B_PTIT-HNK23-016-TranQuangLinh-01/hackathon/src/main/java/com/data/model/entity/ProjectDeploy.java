package com.data.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "project_deploy")
public class ProjectDeploy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_deploy_id;

    @Column(nullable = false, length = 70)
    private String managerName;

    @Column(nullable = false)
    private int totalEmployeeJoined;

    @Column(length = 255)
    private String imageManager;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}