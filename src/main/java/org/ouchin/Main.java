package org.ouchin;

import org.ouchin.presetations.ClientUi;
import org.ouchin.presetations.MaterialUi;
import org.ouchin.presetations.ProjectUi;
import org.ouchin.presetations.WorkForceUi;
import org.ouchin.presetations.menus.ClientMenu;
import org.ouchin.repositories.*;
import org.ouchin.repositories.Impl.ClientRepositoryImpl;
import org.ouchin.repositories.Impl.MaterielRepositoryImpl;
import org.ouchin.repositories.Impl.ProjectRepositoryImpl;
import org.ouchin.repositories.Impl.WorkForceRepositoryImpl;
import org.ouchin.services.*;

public class Main {
    public static void main(String[] args) {
        // Repositories
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        MaterielRepository materialRepository = new MaterielRepositoryImpl();
        WorkForceRepository workForceRepository = new WorkForceRepositoryImpl();

        // Services
        ClientService clientService = new ClientService(clientRepository);
        ProjectService projectService = new ProjectService(projectRepository);
        MaterialService materialService = new MaterialService(materialRepository);
        WorkForceService workForceService = new WorkForceService(workForceRepository);

        // UIs
        ClientUi clientUI = new ClientUi(clientService);
        MaterialUi materialUI = new MaterialUi(materialService);
        WorkForceUi workForceUI = new WorkForceUi(workForceService);
        ProjectUi projectUI = new ProjectUi(projectService, materialUI, workForceUI);

        ClientMenu menu = new ClientMenu(clientUI, projectUI);
        menu.show();
    }
}