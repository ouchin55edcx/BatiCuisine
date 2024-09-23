package org.ouchin.presetations;

import org.ouchin.enums.ComponentType;
import org.ouchin.models.Material;
import org.ouchin.services.MaterialService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MaterialUi {

    private final MaterialService materialService;
    private final Scanner scanner = new Scanner(System.in);

    public MaterialUi(MaterialService materialService) {
        this.materialService = materialService;
    }


    public List<Material> addMaterialsToProject(UUID projectId) {
        List<Material> materials = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("--- Ajout des matériaux ---");
            Material material = new Material();
            material.setProjectId(projectId);
            material.setType(ComponentType.MATERIAL);

            System.out.print("Entrez le nom du matériau : ");
            material.setName(scanner.nextLine());

            System.out.print("Entrez la quantité de ce matériau : ");
            material.setQuantity(Integer.parseInt(scanner.nextLine()));

            System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
            material.setUnitCost(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            material.setTransportCost(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            material.setQualityCoefficient(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le taux de TVA pour ce matériau : ");
            material.setVatRate(Double.parseDouble(scanner.nextLine()));

            materials.add(material);
            System.out.println("Matériau ajouté avec succès !");

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        materialService.saveAll(materials);
        return materials;
    }


}
