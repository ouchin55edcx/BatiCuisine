package org.ouchin.presetations;

import org.ouchin.models.Client;
import org.ouchin.services.ClientService;

import java.util.Optional;
import java.util.Scanner;

public class ClientUi {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientUi(ClientService clientService) {
        this.clientService = clientService;
    }

    public void add() {
        System.out.println("Add a new client:");

        String fullName = getFullNameInput();
        String address = getAddressInput();
        String phoneNumber = getPhoneNumberInput();
        Boolean isProfessional = getIsProfessionalInput();

        clientService.add(fullName, address, phoneNumber, isProfessional);
        System.out.println("New client added successfully!");
    }

    public Optional<Client> searchByName() {
        String fullName = getFullNameInput();
        Optional<Client> clientOptional = clientService.searchByName(fullName);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client found:");
            System.out.println("Client ID: " + client.getId());
            System.out.println("Full Name: " + client.getFullName());
            System.out.println("Address: " + client.getAddress());
            System.out.println("Phone Number: " + client.getPhoneNumber());
        } else {
            System.out.println("No client found with that name.");
        }
        return clientOptional;
    }

    private String getFullNameInput() {
        String fullNameRegex = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
        while (true) {
            System.out.println("Enter full name:");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches(fullNameRegex)) {
                return input;
            }
            System.out.println("Full name cannot be empty. Please try again.");
        }
    }

    private String getAddressInput() {
        String addressRegex = "^[A-Za-z\\s0-9]+$";
        while (true) {
            System.out.println("Enter address:");
            String input = scanner.nextLine();
            if (!input.isEmpty() && input.matches(addressRegex)) {
                return input;
            }
            System.out.println("Invalid address. Please try again!");
        }
    }

    private String getPhoneNumberInput() {
        String phoneNumberRegex = "^\\d{10}$";
        int maxAttempts = 3;
        int count = 1;

        while (count <= maxAttempts) {
            System.out.println("Enter phone number:");
            String input = scanner.nextLine();
            if (!input.isEmpty() && input.matches(phoneNumberRegex)) {
                return input;
            }
            System.out.println("Invalid phone number. Try again!");
            count++;
        }
        System.out.println("You have reached " + maxAttempts + " failed attempts.");
        return null;
    }

    private Boolean getIsProfessionalInput() {
        while (true) {
            System.out.println("Is this client professional? (yes/no)");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            }
            System.out.println("Invalid input. Please enter 'yes' or 'no'!");
        }
    }
}
