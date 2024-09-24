package org.ouchin.repositories;

import org.ouchin.enums.ProjectStatus;
import org.ouchin.models.*;
import org.ouchin.presetations.ProjectUi;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    Project add(Project project);
    List<Project> getAll();
    List<Project> getByClientId(UUID clientId);
    void updateStatus(UUID projectId, ProjectStatus newStatus);
    void delete(UUID projectId);
}
