package org.ouchin.presetations;

import org.ouchin.models.Client;
import org.ouchin.models.Project;
import org.ouchin.models.WorkForce;
import org.ouchin.services.ProjectService;

import java.security.KeyStore;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProjectUi {
    private final ProjectService projectService;
    private final MaterialUi materialUi;
    private final WorkForceUi workForceUi;
    private final Scanner scanner = new Scanner(System.in);

    public ProjectUi(ProjectService projectService, MaterialUi materialUi, WorkForceUi workForceUi) {
        this.projectService = projectService;
        this.materialUi = materialUi;
        this.workForceUi = workForceUi;
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

        System.out.println("Project created successfully!");
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

    public void displayProjectsForClient(UUID clientId) {
        List<Project> projects = projectService.getProjectsForClient(clientId);
        if (projects.isEmpty()) {
            System.out.println("No projects found for this client.");
        } else {
            System.out.println("Projects for client " + clientId + ":");
            for (Project project : projects) {
                System.out.println(project);
            }
        }
    }
}
