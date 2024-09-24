package org.ouchin.services;

import org.ouchin.models.Material;
import org.ouchin.repositories.MaterielRepository;
import org.ouchin.repositories.ProjectRepository;

import java.util.List;
import java.util.UUID;

public class MaterialService {

    private final MaterielRepository materielRepository;
    private final ProjectRepository projectRepository;


    public MaterialService(MaterielRepository materielRepository, ProjectRepository projectRepository) {
        this.materielRepository = materielRepository;
        this.projectRepository = projectRepository;
    }

    public void saveAll(List<Material> materials) {
        for (Material material : materials) {
            materielRepository.addMateriel(material);
        }
    }

    public Double calculateTotalOfMaterials(UUID projectId) {
        List<Material> materials = materielRepository.getMaterialComponentsByProjectId(projectId);
        return materials.stream()
                .mapToDouble(Material::total)
                .sum();
    }

    public List<Material> findAllByProjectId(UUID id) {
        return materielRepository.getMaterialComponentsByProjectId(id);
    }

}
