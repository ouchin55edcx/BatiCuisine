package org.ouchin.presetations.menus;

import org.ouchin.presetations.ClientUi;

import java.util.Scanner;

public class ClientMenu {

    private final ClientUi clientUi;
    private final Scanner scanner = new Scanner(System.in);

    public ClientMenu(ClientUi clientUi) {
        this.clientUi = clientUi;
    }

    public void show(){
        boolean exit = false;
        while (!exit){
            printMenuOptions();
            int choice = getChoice();

            switch (choice){
                case 1 ->clientUi.add();
                case 2 ->clientUi.searchByName();
                case 0 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");

            }
        }
        System.out.println("Exiting client management.");
    }

    private void printMenuOptions(){
        System.out.println("===== Client Management Menu =====");
        System.out.println("1. Add New Client");
        System.out.println("2. Search Client");
        System.out.println("0. Exit");
        System.out.println("Please choose an option:");
    }

    private int getChoice(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
