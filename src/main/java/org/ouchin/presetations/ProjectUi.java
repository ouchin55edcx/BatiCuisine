package org.ouchin.presetations;

import org.ouchin.enums.ProjectStatus;
import org.ouchin.models.Client;
import org.ouchin.models.Estimate;
import org.ouchin.models.Project;
import org.ouchin.models.WorkForce;
import org.ouchin.services.EstimateService;
import org.ouchin.services.ProjectService;

import java.time.LocalDate;
import java.util.Arrays;
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

        System.out.println("Do you want to add some materiels: ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            materialUi.addMaterialsToProject(createdProject.getId());
        }

        System.out.println("Do you want to add new Workers");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            workForceUi.addWorkForceToProject(createdProject.getId());
        }
        Double total = projectService.getTotal(createdProject.getId());

        workForceUi.showWorkForceByProjectId(createdProject.getId());
        materialUi.showMaterialsByProjectId(createdProject.getId());
        System.out.println("The total estimated cost is: " + total);


        System.out.println("Do you want to save this estimate? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            saveEstimateWithTotal(createdProject.getId(), total.floatValue());
        }

        System.out.println("Project created successfully!");
    }

    private void saveEstimateWithTotal(UUID projectId, float totalAmount) {
        System.out.println("Saving estimate for project ID: " + projectId);

        System.out.println("Enter the issue date (yyyy-MM-dd):");
        LocalDate issueDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Enter the validity date (yyyy-MM-dd):");
        LocalDate validityDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Is the estimate accepted? (y/n): ");
        boolean isAccepted = scanner.nextLine().trim().equalsIgnoreCase("y");

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


    private void updateProjectStatus(List<Project> projects) {
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

        // Display available project statuses
        System.out.print("Enter new project status (" +
                Arrays.toString(ProjectStatus.values()) + "): ");
        String newStatus = scanner.nextLine().trim();

        try {
            // Convert input to ProjectStatus enum
            ProjectStatus updatedStatus = ProjectStatus.valueOf(newStatus.toUpperCase());

            // Call service to update the project status
            projectService.updateProjectStatus(projectId, updatedStatus);

            System.out.println("Project status updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status. Project status unchanged.");
        }
    }


    public void displayProjectsForClient(UUID clientId) {
        List<Project> projects = projectService.getProjectsForClient(clientId);

        if (projects.isEmpty()) {
            System.out.println("No projects found for this client.");
        } else {
            System.out.println("Projects for client " + clientId + ":");
            for (Project project : projects) {
                // Assuming Project class has appropriate getter methods
                System.out.println("id: " + project.getId());
                System.out.println("Project Name: " + project.getProjectName());
                System.out.println("Profit Margin: " + project.getProfitMargin());
                System.out.println("Status: " + project.getStatus());
                System.out.println("-----------------------------------");
            }
            handleProjectOperations(projects);
        }
    }


    private void handleProjectOperations(List<Project> projects) {
        while (true) {
            System.out.println("\n--- Project Operations ---");
            System.out.println("1. Update a project");
            System.out.println("2. Delete a project");
            System.out.println("3. Get estimate by project ID");
            System.out.println("0. Return to main menu");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> updateProjectStatus(projects);
                case 2 -> deleteProject(projects);
                case 3 -> getEstimateByProjectId(projects);



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


    private void getEstimateByProjectId(List<Project> projects) {
        System.out.print("Enter the ID of the project: ");
        UUID projectId = UUID.fromString(scanner.nextLine().trim());

        Project project = projects.stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst()
                .orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        Estimate estimate = estimateService.getEstimateByProjectId(projectId);
        if (estimate == null) {
            System.out.println("Materials for the project:");
            materialUi.showMaterialsByProjectId(project.getId());

            System.out.println("Workforce for the project:");
            workForceUi.showWorkForceByProjectId(project.getId());
            System.out.println("No estimate found for the project.");
            System.out.println("Do you want to create a new estimate? (y/n)");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Double total = projectService.getTotal(project.getId());
                saveEstimateWithTotal(project.getId(), total.floatValue());
            }
        } else {
            System.out.println("Estimate for project " + project.getProjectName() + ":");
            System.out.println("Estimated Amount: " + estimate.getEstimatedAmount());
            System.out.println("Issue Date: " + estimate.getIssueDate());
            System.out.println("Validity Date: " + estimate.getValidityDate());
            System.out.println("Is Accepted: " + estimate.isAccepted());

            System.out.println("Materials for the project:");
            materialUi.showMaterialsByProjectId(project.getId());

            System.out.println("Workforce for the project:");
            workForceUi.showWorkForceByProjectId(project.getId());

            System.out.println("\nDo you want to update the acceptance status of this estimate? (y/n)");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                updateEstimateAcceptance(project.getId(), estimate);
            } else {
                System.out.println("Returning to main menu.");
            }
        }
    }

    private void updateEstimateAcceptance(UUID projectId, Estimate estimate) {
        System.out.print("Enter the new acceptance status (true/false): ");
        boolean newAcceptanceStatus = Boolean.parseBoolean(scanner.nextLine().trim());

        estimateService.updateEstimateAcceptance(projectId, newAcceptanceStatus);

        System.out.println("Estimate acceptance status updated successfully.");
    }}
