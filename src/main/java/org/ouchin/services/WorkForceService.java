package org.ouchin.services;

import org.ouchin.models.WorkForce;
import org.ouchin.repositories.ProjectRepository;
import org.ouchin.repositories.WorkForceRepository;

import java.util.List;
import java.util.UUID;

public class WorkForceService {

    private final WorkForceRepository workForceRepository;
    private final ProjectRepository projectRepository;


    public WorkForceService(WorkForceRepository workForceRepository, ProjectRepository projectRepository) {
        this.workForceRepository = workForceRepository;
        this.projectRepository = projectRepository;
    }


    public void saveAll(List<WorkForce> workForces) {
        workForceRepository.saveAll(workForces);
    }

    public Double calculateTotalOfWorkforce(UUID projectId) {
        List<WorkForce> workforces = workForceRepository.getWorkforceComponentsByProjectId(projectId);
        return workforces.stream()
                .mapToDouble(WorkForce::total)
                .sum();
    }
}
