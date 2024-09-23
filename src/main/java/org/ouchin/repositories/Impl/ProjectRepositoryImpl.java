    package org.ouchin.repositories.Impl;

    import org.ouchin.config.DatabaseConfig;
    import org.ouchin.models.Project;
    import org.ouchin.repositories.ProjectRepository;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.sql.Types;
    import java.util.UUID;

    public class ProjectRepositoryImpl implements ProjectRepository {

        private final Connection connection = DatabaseConfig.getInstance().getConnection();
        private final String tableName = "project";


        @Override
        public void add(Project project) {
            String query = "INSERT INTO " + tableName + " (id, project_name, profit_margin, status, client_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setObject(1, project.getId());
                pstmt.setString(2, project.getProjectName());
                pstmt.setDouble(3, project.getProfitMargin());
                pstmt.setObject(4, project.getStatus().name(), Types.OTHER);
                pstmt.setObject(5, project.getClientId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
