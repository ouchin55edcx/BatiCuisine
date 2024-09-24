package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.Estimate;
import org.ouchin.repositories.EstimateRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

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

    @Override
    public Estimate findByProjectId(UUID projectId) {
        String query = "SELECT * FROM " + tableName + " WHERE projectId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                float estimatedAmount = resultSet.getFloat("estimatedAmount");
                LocalDate issueDate = resultSet.getObject("issueDate", LocalDate.class);
                LocalDate validityDate = resultSet.getObject("validityDate", LocalDate.class);
                boolean isAccepted = resultSet.getBoolean("isAccepted");

                return new Estimate(id, estimatedAmount, issueDate, validityDate, isAccepted, projectId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving estimate by project ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Estimate estimate) {
        String query = "UPDATE " + tableName + " SET isAccepted = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, estimate.isAccepted());
            statement.setObject(2, estimate.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estimate updated successfully.");
            } else {
                System.out.println("Failed to update estimate.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating estimate: " + e.getMessage());
        }
    }



}
