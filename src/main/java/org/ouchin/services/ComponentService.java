package org.ouchin.services;

import org.ouchin.models.Component;
import org.ouchin.repositories.ComponentRepository;

public class ComponentService {

    private final ComponentRepository componentRepository;


    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public  void addComponent(Component component){
        componentRepository.save(component);
    }
}
