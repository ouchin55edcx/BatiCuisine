package org.ouchin.repositories;

import org.ouchin.models.Client;

import java.util.Optional;

public interface ClientRepository {
    void add(Client client);
    Optional<Client> findByName(String fullName);
}
