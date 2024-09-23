package org.ouchin;

import org.ouchin.presetations.ClientUi;
import org.ouchin.presetations.ProjectUi;
import org.ouchin.presetations.menus.ClientMenu;
import org.ouchin.repositories.ClientRepository;
import org.ouchin.repositories.ProjectRepository;
import org.ouchin.repositories.Impl.ClientRepositoryImpl;
import org.ouchin.repositories.Impl.ProjectRepositoryImpl;
import org.ouchin.services.ClientService;
import org.ouchin.services.ProjectService;

public class Main {
    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();

        ClientService clientService = new ClientService(clientRepository);
        ProjectService projectService = new ProjectService(projectRepository);

        ClientUi clientUi = new ClientUi(clientService);
        ProjectUi projectUi = new ProjectUi(projectService);

        ClientMenu menu = new ClientMenu(clientUi, projectUi);
        menu.show();
    }
}