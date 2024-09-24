package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.enums.ComponentType;
import org.ouchin.models.Material;
import org.ouchin.repositories.MaterielRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private List<Material> getMaterialComponents(UUID projectId) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE project_id = ? AND type = ?::component_type";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, projectId);
            pstmt.setObject(2, ComponentType.MATERIAL, Types.OTHER);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material(
                            rs.getObject("id", UUID.class),
                            rs.getString("name"),
                            ComponentType.MATERIAL,
                            rs.getFloat("vat_rate"),
                            rs.getObject("project_id", UUID.class),
                            rs.getDouble("unit_cost"),
                            rs.getInt("quantity"),
                            rs.getDouble("transport_cost"),
                            rs.getDouble("quality_coefficient")
                    );
                    materials.add(material);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materials;
    }

    @Override
    public List<Material> getMaterialComponentsByProjectId(UUID projectId) {
        return getMaterialComponents(projectId);
    }
}
