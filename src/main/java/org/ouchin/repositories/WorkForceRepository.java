package org.ouchin.repositories;

import org.ouchin.models.WorkForce;

import java.util.List;
import java.util.UUID;

public interface WorkForceRepository {

    WorkForce addLabor(WorkForce workForce) ;
    List<WorkForce> saveAll(List<WorkForce> workForces);
    List<WorkForce> getWorkforceComponentsByProjectId(UUID projectId);
}
