package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.Estimate;
import org.ouchin.repositories.EstimateRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstimateRepositoryImpl implements EstimateRepository {

    private final Connection connection = DatabaseConfig.getInstance().getConnection();
    private final String tableName = "estimate";


    @Override
    public void save(Estimate estimate) {
        String query = "INSERT INTO " + tableName + " (id, estimatedAmount, issueDate, validityDate, isAccepted, projectId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, estimate.getId());
            statement.setFloat(2, estimate.getEstimatedAmount());
            statement.setObject(3, estimate.getIssueDate());  // LocalDate is supported by JDBC in Postgres
            statement.setObject(4, estimate.getValidityDate());
            statement.setBoolean(5, estimate.isAccepted());
            statement.setObject(6, estimate.getProjectId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estimate saved successfully.");
            } else {
                System.out.println("Failed to save estimate.");
            }
        } catch (SQLException e) {
            System.err.println("Error saving estimate: " + e.getMessage());
        }
    }
}
