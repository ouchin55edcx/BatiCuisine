    package org.ouchin.presetations.menus;

    import org.ouchin.models.Client;
    import org.ouchin.presetations.ClientUi;
    import org.ouchin.presetations.ProjectUi;

    import java.util.Optional;
    import java.util.Scanner;

    public class ClientMenu {
        private final ClientUi clientUi;
        private final ProjectUi projectUi;
        private final Scanner scanner = new Scanner(System.in);

        public ClientMenu(ClientUi clientUi, ProjectUi projectUi) {
            this.clientUi = clientUi;
            this.projectUi = projectUi;
        }

        public void show() {
            boolean exit = false;
            while (!exit) {
                printMenuOptions();
                int choice = getChoice();

                switch (choice) {
                    case 1 -> createOrSearchClientAndProject();
                    case 2 -> projectUi.displayAllProjects();
                    case 3 -> displayProjectsForSpecificClient();
                    case 0 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            System.out.println("Returning to main menu.");
        }

        private int getChoice() {
            while (true) {
                try {
                    return Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        }

        private void printMenuOptions() {
            System.out.println("\n===== Client Management Menu =====");
            System.out.println("1. Create Project for Client");
            System.out.println("2. Display All Projects");
            System.out.println("3. Display Projects for Specific Client");
            System.out.println("0. Return to Main Menu");
            System.out.println("Please choose an option:");
        }


        private void createOrSearchClientAndProject() {
            System.out.println("1. Search for an existing client");
            System.out.println("2. Add a new client");
            int clientChoice = getChoice();

            Optional<Client> clientOptional;
            if (clientChoice == 1) {
                clientOptional = clientUi.searchByName();
            } else {
                clientUi.add();
                clientOptional = clientUi.searchByName();
            }

            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                System.out.println("Would you like to continue with this client? (y/n)");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    projectUi.createProjectForClient(client);
                }
            }
        }

        private void displayProjectsForSpecificClient() {
            Optional<Client> clientOptional = clientUi.searchByName();
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                projectUi.displayProjectsForClient(client.getId());
            } else {
                System.out.println("Client not found.");
            }
        }
    }
