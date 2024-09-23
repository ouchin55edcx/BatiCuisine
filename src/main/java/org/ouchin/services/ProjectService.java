package org.ouchin.services;

import org.ouchin.models.Project;
import org.ouchin.enums.ProjectStatus;
import org.ouchin.repositories.ProjectRepository;

import java.util.UUID;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(UUID clientId, String projectName, double profitMargin) {
        UUID id = UUID.randomUUID();
        ProjectStatus status = ProjectStatus.IN_PROGRESS;

        Project project = new Project(id, projectName, profitMargin, status, clientId);
        projectRepository.add(project);

        System.out.println("Project created with ID: " + id + " and status: " + status);
    }
}
