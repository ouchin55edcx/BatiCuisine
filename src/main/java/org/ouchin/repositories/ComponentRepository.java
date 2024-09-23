package org.ouchin.repositories;

import org.ouchin.models.Component;

import java.util.UUID;

public interface ComponentRepository {
    void save(Component component);
    Component findById(UUID id);
}
