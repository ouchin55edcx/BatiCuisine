package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.enums.ComponentType;
import org.ouchin.models.Material;
import org.ouchin.models.WorkForce;
import org.ouchin.repositories.WorkForceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<WorkForce> getWorkforceComponentsByProjectId(UUID projectId) {
        return getWorkForceComponents(projectId);
    }



    private List<WorkForce> getWorkForceComponents(UUID projectId) {
        List<WorkForce> workForces = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE project_id = ? AND type = ?::component_type";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, projectId);
            pstmt.setObject(2, ComponentType.LABOR, Types.OTHER);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WorkForce workForce = new WorkForce(
                            rs.getObject("id", UUID.class),
                            rs.getString("name"),
                            ComponentType.LABOR,
                            rs.getFloat("vat_rate"),
                            rs.getObject("project_id", UUID.class),
                            rs.getDouble("hourly_rate"),
                            rs.getDouble("work_hours"),
                            rs.getDouble("worker_productivity")
                    );
                    workForces.add(workForce);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workForces;
    }

}