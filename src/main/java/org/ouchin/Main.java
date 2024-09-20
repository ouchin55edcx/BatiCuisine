package org.ouchin;

import org.ouchin.presetations.ClientUi;
import org.ouchin.presetations.menus.ClientMenu;
import org.ouchin.repositories.ClientRepository;
import org.ouchin.repositories.Impl.ClientRepositoryImpl;
import org.ouchin.services.ClientService;


public class Main {
    public static void main(String[] args) {

        ClientRepository clientRepository = new ClientRepositoryImpl();
        ClientService clientService = new ClientService(clientRepository);
        ClientUi clientUi = new ClientUi(clientService);


        ClientMenu menu = new ClientMenu(clientUi);
        menu.show();
    }
}