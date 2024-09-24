package org.ouchin.repositories;

import org.ouchin.models.Material;

import java.util.List;
import java.util.UUID;

public interface MaterielRepository {

    void addMateriel(Material materiel);
    void saveAll(List<Material> materiels);
    List<Material> getMaterialComponentsByProjectId(UUID projectId);
}
