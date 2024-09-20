package org.ouchin.repositories.Impl;

import org.ouchin.config.DatabaseConfig;
import org.ouchin.models.Client;
import org.ouchin.repositories.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection = DatabaseConfig.getInstance().getConnection();
    private final String tableName = "client";

    @Override
    public void add(Client client) {
        String query = "INSERT INTO "+tableName+" (id, fullName, address, phoneNumber, isProfessional) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)){

            pstmt.setObject(1, client.getId());
            pstmt.setString(2, client.getFullName());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getPhoneNumber());
            pstmt.setBoolean(5, client.getProfessional());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
