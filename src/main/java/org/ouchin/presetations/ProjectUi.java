package org.ouchin.presetations;

import org.ouchin.models.Client;
import org.ouchin.models.Project;
import org.ouchin.models.WorkForce;
import org.ouchin.services.EstimateService;
import org.ouchin.services.ProjectService;

import java.security.KeyStore;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProjectUi {
    private final ProjectService projectService;
    private final MaterialUi materialUi;
    private final WorkForceUi workForceUi;
    private final EstimateService estimateService;

    private final Scanner scanner = new Scanner(System.in);

    public ProjectUi(ProjectService projectService, MaterialUi materialUi, WorkForceUi workForceUi, EstimateService estimateService) {
        this.projectService = projectService;
        this.materialUi = materialUi;
        this.workForceUi = workForceUi;
        this.estimateService = estimateService;
    }

    public void createProjectForClient(Client client) {
        System.out.println("Creating a new project for client: " + client.getFullName());

        String projectName = getProjectNameInput();
        Double profitMargin = getProfitMarginInput();
        Project createdProject = projectService.addProject(client.getId(), projectName, profitMargin);

        // System.out.println("Project created with ID: " + id + " and status: " + status);

        System.out.println("Do you want to add some materiels: ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            materialUi.addMaterialsToProject(createdProject.getId());
        }

        System.out.println("Do you want to add new Workers");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            workForceUi.addWorkForceToProject(createdProject.getId());
        }
        Double total = projectService.getTotal(createdProject.getId());
        System.out.println("here is the total " + total);

        // Ask if the user wants to save the estimate with the total amount
        System.out.println("Do you want to save this estimate? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            saveEstimateWithTotal(createdProject.getId(), total.floatValue());
        }

        //Todo : display all project details with client info
        //Todo :
        System.out.println("Project created successfully!");
    }

    private void saveEstimateWithTotal(UUID projectId, float totalAmount) {
        System.out.println("Saving estimate for project ID: " + projectId);

        // Input for issue date
        System.out.println("Enter the issue date (yyyy-MM-dd):");
        LocalDate issueDate = LocalDate.parse(scanner.nextLine().trim());

        // Input for validity date
        System.out.println("Enter the validity date (yyyy-MM-dd):");
        LocalDate validityDate = LocalDate.parse(scanner.nextLine().trim());

        // Optionally accept or reject the estimate
        System.out.println("Is the estimate accepted? (y/n): ");
        boolean isAccepted = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Pass the values to the service to save the estimate
        estimateService.saveEstimateWithTotal(projectId, totalAmount, issueDate, validityDate, isAccepted);

        System.out.println("Estimate saved with a total amount of: " + totalAmount);
    }

    private String getProjectNameInput() {
        System.out.println("Enter project name:");
        return scanner.nextLine().trim();
    }

    private Double getProfitMarginInput() {
        System.out.println("Enter the profit margin for the project:");
        return Double.parseDouble(scanner.nextLine().trim());
    }


    public void displayAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            System.out.println("All Projects:");
            for (Project project : projects) {
                System.out.println(project);

            }

        }

    }


    private void updateProject(List<Project> projects) {
        System.out.print("Enter the ID of the project to update: ");
        UUID projectId = UUID.fromString(scanner.nextLine().trim());

        Project projectToUpdate = projects.stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst()
                .orElse(null);

        if (projectToUpdate == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.print("Enter new project name (press enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            projectToUpdate.setProjectName(newName);
        }

        System.out.print("Enter new profit margin (press enter to keep current): ");
        String newMargin = scanner.nextLine().trim();
        if (!newMargin.isEmpty()) {
            projectToUpdate.setProfitMargin(Double.parseDouble(newMargin));
        }

        projectService.updateProject(projectToUpdate);
        System.out.println("Project updated successfully.");
    }

    public void displayProjectsForClient(UUID clientId) {
        List<Project> projects = projectService.getProjectsForClient(clientId);
        if (projects.isEmpty()) {
            System.out.println("No projects found for this client.");
        } else {
            System.out.println("Projects for client " + clientId + ":");
            for (Project project : projects) {
                System.out.println(project);
            }
            handleProjectOperations(projects);

        }
    }

    private void handleProjectOperations(List<Project> projects) {
        while (true) {
            System.out.println("\n--- Project Operations ---");
            System.out.println("1. Update a project");
            System.out.println("2. Delete a project");
            System.out.println("0. Return to main menu");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> updateProject(projects);
                case 2 -> deleteProject(projects);

                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void deleteProject(List<Project> projects) {
        System.out.print("Enter the ID of the project to delete: ");
        UUID projectId = UUID.fromString(scanner.nextLine().trim());

        Project projectToDelete = projects.stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst()
                .orElse(null);

        if (projectToDelete == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this project? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            projectService.deleteProject(projectId);
            System.out.println("Project deleted successfully.");
            projects.remove(projectToDelete);
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }
}
