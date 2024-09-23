package org.ouchin.services;

import org.ouchin.models.WorkForce;
import org.ouchin.repositories.WorkForceRepository;

import java.util.List;

public class WorkForceService {

    private final WorkForceRepository workForceRepository;

    public WorkForceService(WorkForceRepository workForceRepository) {
        this.workForceRepository = workForceRepository;
    }


    public void saveAll(List<WorkForce> workForces) {
        workForceRepository.saveAll(workForces);
    }
}
