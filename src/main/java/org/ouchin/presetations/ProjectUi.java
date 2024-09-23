package org.ouchin.presetations;

import org.ouchin.models.Client;
import org.ouchin.services.ProjectService;

import java.util.Scanner;

public class ProjectUi {
    private final ProjectService projectService;
    private final Scanner scanner = new Scanner(System.in);

    public ProjectUi(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void createProjectForClient(Client client) {
        System.out.println("Creating a new project for client: " + client.getFullName());

        String projectName = getProjectNameInput();
        Double profitMargin = getProfitMarginInput();
        projectService.addProject(client.getId(), projectName, profitMargin);
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
}
