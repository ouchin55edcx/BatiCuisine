package org.ouchin.repositories;

import org.ouchin.models.WorkForce;

import java.util.List;

public interface WorkForceRepository {

    WorkForce addLabor(WorkForce workForce) ;
    List<WorkForce> saveAll(List<WorkForce> workForces);
}
