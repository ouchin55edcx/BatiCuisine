package org.ouchin.services;

import org.ouchin.models.Material;
import org.ouchin.repositories.MaterielRepository;

import java.util.List;

public class MaterialService {

    private final MaterielRepository materielRepository;


    public MaterialService(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    public void saveAll(List<Material> materials) {
        for (Material material : materials) {
            materielRepository.addMateriel(material);
        }
    }


}
