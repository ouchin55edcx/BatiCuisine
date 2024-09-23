package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.WorkForce;
import org.ouchin.repositories.WorkForceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WorkForceRepositoryImpl implements WorkForceRepository {

    private final Connection connection = DatabaseConfig.getInstance().getConnection();
    private final String tableName = "workforce";


    @Override
    public WorkForce addLabor(WorkForce workForce) {
        String sql = "INSERT INTO " + tableName +
                " (id, name, type, vat_rate, project_id, hourly_rate, work_hours, worker_productivity) " +
                "VALUES (?, ?, ?::component_type, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, workForce.getId());
            stmt.setString(2, workForce.getName());
            stmt.setString(3, workForce.getType().toString());
            stmt.setDouble(4, workForce.getVatRate());
            stmt.setObject(5, workForce.getProjectId());
            stmt.setDouble(6, workForce.getHourlyRate());
            stmt.setDouble(7, workForce.getWorkHours());
            stmt.setDouble(8, workForce.getWorkerProductivity());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return workForce;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<WorkForce> saveAll(List<WorkForce> workForces) {
        for (WorkForce workForce : workForces) {
            addLabor(workForce);
        }
        return workForces;
    }

}