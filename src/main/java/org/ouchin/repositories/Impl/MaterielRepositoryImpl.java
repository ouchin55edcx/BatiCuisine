package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.Material;
import org.ouchin.repositories.MaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaterielRepositoryImpl implements MaterielRepository {

    private final Connection connection = DatabaseConfig.getInstance().getConnection();
    private final String tableName = "material";



    @Override
    public void addMateriel(Material material) {
        String sql = "INSERT INTO " + tableName +
                " (id, name, type, vat_rate, project_id, unit_cost, quantity, transport_cost, quality_coefficient) " +
                "VALUES (?, ?, ?::component_type, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, material.getId());
            stmt.setString(2, material.getName());
            stmt.setObject(3, material.getType().toString());
            stmt.setDouble(4, material.getVatRate());
            stmt.setObject(5, material.getProjectId());
            stmt.setDouble(6, material.getUnitCost());
            stmt.setInt(7, material.getQuantity());
            stmt.setDouble(8, material.getTransportCost());
            stmt.setDouble(9, material.getQualityCoefficient());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<Material> materiels) {
        for (Material materiel : materiels) {
            addMateriel(materiel);
        }

    }
}
