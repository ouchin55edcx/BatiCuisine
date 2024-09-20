package org.ouchin.services;

import org.ouchin.models.Client;
import org.ouchin.repositories.ClientRepository;

import java.util.Optional;
import java.util.UUID;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void add(String fullName, String address, String phoneNumber, Boolean isProfessional){
        UUID id = UUID.randomUUID();
        Client newClient = new Client(id, fullName, address, phoneNumber, isProfessional);
        clientRepository.add(newClient);
    }

    public Optional<Client> searchByName(String fullName){
        return clientRepository.findByName(fullName);
    }
}
