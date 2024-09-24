package org.ouchin;

import org.ouchin.presetations.*;
import org.ouchin.presetations.menus.ClientMenu;
import org.ouchin.repositories.*;
import org.ouchin.repositories.Impl.*;
import org.ouchin.services.*;

public class Main {
    public static void main(String[] args) {
        // Repositories
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        MaterielRepository materialRepository = new MaterielRepositoryImpl();
        WorkForceRepository workForceRepository = new WorkForceRepositoryImpl();
        EstimateRepository estimateRepository = new EstimateRepositoryImpl();

        // Services
        ClientService clientService = new ClientService(clientRepository);
        MaterialService materialService = new MaterialService(materialRepository, projectRepository);
        WorkForceService workForceService = new WorkForceService(workForceRepository, projectRepository);
        ProjectService projectService = new ProjectService(projectRepository, materialService, workForceService);
        EstimateService estimateService = new EstimateService(estimateRepository);

        // UIs
        ClientUi clientUI = new ClientUi(clientService);
        MaterialUi materialUI = new MaterialUi(materialService);
        WorkForceUi workForceUI = new WorkForceUi(workForceService);
        ProjectUi projectUI = new ProjectUi(projectService, materialUI, workForceUI, estimateService);

        ClientMenu menu = new ClientMenu(clientUI, projectUI);
        menu.show();
    }
}