package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.Component;
import org.ouchin.repositories.ComponentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ComponentRepositoryImpl implements ComponentRepository {

    private final Connection connection = DatabaseConfig.getInstance().getConnection();
    private final String tableName = "component";

    @Override
    public void save(Component component) {
        if (component.getId() == null) {
            component.setId(UUID.randomUUID());
        }
        String sql = "INSERT INTO Component (id, name, type, vat_rate ,project_id) " +
                "VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, component.getId());
            stmt.setString(2, component.getName());
            stmt.setObject(3, component.getType());
            stmt.setDouble(4, component.getVatRate());
            stmt.setObject(5, component.getProjectId());

            stmt.executeUpdate();
            System.out.println("Component added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component findById(UUID id) {
        return null;
    }
}
