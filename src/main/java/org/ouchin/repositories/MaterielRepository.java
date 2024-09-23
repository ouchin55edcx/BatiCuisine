package org.ouchin.repositories;

import org.ouchin.models.Material;

import java.util.List;

public interface MaterielRepository {

    void addMateriel(Material materiel);
    void saveAll(List<Material> materiels);
}
