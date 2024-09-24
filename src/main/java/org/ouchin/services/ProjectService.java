package org.ouchin.services;

import org.ouchin.models.Material;
import org.ouchin.models.Project;
import org.ouchin.enums.ProjectStatus;
import org.ouchin.models.WorkForce;
import org.ouchin.repositories.MaterielRepository;
import org.ouchin.repositories.ProjectRepository;
import org.ouchin.repositories.WorkForceRepository;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

public class   ProjectService {
    private final ProjectRepository projectRepository;
    private final MaterialService materialService;
    private final WorkForceService workForceRepository;

    public ProjectService(ProjectRepository projectRepository, MaterialService materialService, WorkForceService workForceRepository) {
        this.projectRepository = projectRepository;
        this.materialService = materialService;
        this.workForceRepository = workForceRepository;
    }

    public Project addProject(UUID clientId, String projectName, double profitMargin) {
        UUID id = UUID.randomUUID();
        ProjectStatus status = ProjectStatus.IN_PROGRESS;

        Project project = new Project(id, projectName, profitMargin, status, clientId);
        return projectRepository.add(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAll();
    }

    public List<Project> getProjectsForClient(UUID clientId) {
        return projectRepository.getByClientId(clientId);
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    public void deleteProject(UUID projectId) {
        projectRepository.delete(projectId);
    }

//    public List<Material> getMaterialComponentsForProject(UUID projectId) {
//        return materielRepository.getMaterialComponentsByProjectId(projectId);
//    }
//
//    public List<WorkForce> getWorkforceComponentsForProject(UUID projectId) {
//        return wo.getWorkforceComponentsByProjectId(projectId);
//    }

    public Double getTotal(UUID id) {
        return materialService.calculateTotalOfMaterials(id) + workForceRepository.calculateTotalOfWorkforce(id);
    }
}
