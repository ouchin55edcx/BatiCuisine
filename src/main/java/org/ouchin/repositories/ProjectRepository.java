package org.ouchin.repositories;

import org.ouchin.models.Project;
import org.ouchin.presetations.ProjectUi;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    Project add(Project project);
    List<Project> getAll();
    List<Project> getByClientId(UUID clientId);
    void update(Project project);

}
