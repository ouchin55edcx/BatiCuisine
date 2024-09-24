package org.ouchin.repositories;

import org.ouchin.models.Component;
import org.ouchin.models.Material;
import org.ouchin.models.Project;
import org.ouchin.models.WorkForce;
import org.ouchin.presetations.ProjectUi;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    Project add(Project project);
    List<Project> getAll();
    List<Project> getByClientId(UUID clientId);
    void update(Project project);
    void delete(UUID projectId);

}
