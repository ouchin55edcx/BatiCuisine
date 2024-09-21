package org.ouchin.services;

import org.ouchin.enums.ProjectStatus;
import org.ouchin.models.Client;
import org.ouchin.models.Project;
import org.ouchin.repositories.ClientRepository;
import org.ouchin.repositories.ProjectRepository;

import java.util.UUID;

public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }


}
