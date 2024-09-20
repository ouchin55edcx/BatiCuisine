package org.ouchin;

import org.ouchin.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

        Connection connection = databaseConfig.getConnection();

        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error while checking connection: " + e.getMessage());
        }
    }
}