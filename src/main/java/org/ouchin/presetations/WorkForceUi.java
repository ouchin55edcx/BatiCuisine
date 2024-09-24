package org.ouchin.presetations;

import org.ouchin.enums.ComponentType;
import org.ouchin.models.WorkForce;
import org.ouchin.services.WorkForceService;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class WorkForceUi {
    private final WorkForceService workForceService;
    private final Scanner scanner = new Scanner(System.in);

    public WorkForceUi(WorkForceService workForceService) {
        this.workForceService = workForceService;
    }

    public List<WorkForce> addWorkForceToProject(UUID projectId) {
        List<WorkForce> workForces = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("--- Ajout de la main-d'œuvre ---");
            WorkForce workForce = new WorkForce();
            workForce.setProjectId(projectId);
            workForce.setType(ComponentType.LABOR);

            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            workForce.setName(scanner.nextLine());

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            workForce.setHourlyRate(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le nombre d'heures travaillées : ");
            workForce.setWorkHours(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            workForce.setWorkerProductivity(Double.parseDouble(scanner.nextLine()));

            System.out.print("Entrez le taux de TVA pour cette main-d'œuvre : ");
            workForce.setVatRate(Double.parseDouble(scanner.nextLine()));

            workForces.add(workForce);
            System.out.println("Main-d'œuvre ajoutée avec succès !");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        workForceService.saveAll(workForces);
        return workForces;
    }

    public void showWorkForceByProjectId(UUID id) {
        List<WorkForce> workForces = workForceService.findAllByProjectId(id);

        System.out.println("--- Main-d'œuvre du projet ---");
        for (WorkForce workForce : workForces) {
            System.out.println("Nom : " + workForce.getName());
            System.out.println("Taux horaire : " + workForce.getHourlyRate() + " €/h");
            System.out.println("Heures travaillées : " + workForce.getWorkHours() + " h");
            System.out.println("Productivité : " + workForce.getWorkerProductivity());
            System.out.println("Coût total : " + (workForce.getHourlyRate() * workForce.getWorkHours() * workForce.getWorkerProductivity()) + " €");
            System.out.println();
        }

        double totalLaborCost = workForceService.calculateTotalOfWorkforce(id);
        System.out.println("Total de la main-d'œuvre : " + totalLaborCost + " €");
    }
}
