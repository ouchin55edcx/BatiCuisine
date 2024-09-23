    package org.ouchin.repositories.Impl;

    import org.ouchin.config.DatabaseConfig;
    import org.ouchin.enums.ProjectStatus;
    import org.ouchin.models.Project;
    import org.ouchin.repositories.ProjectRepository;

    import java.sql.*;
    import java.util.UUID;

    public class ProjectRepositoryImpl implements ProjectRepository {

        private final Connection connection = DatabaseConfig.getInstance().getConnection();
        private final String tableName = "project";


        @Override
        public Project add(Project project) {
            String query = "INSERT INTO " + tableName + " (id, project_name, profit_margin, status, client_id) VALUES (?, ?, ?, ?, ?) returning *";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setObject(1, project.getId());
                pstmt.setString(2, project.getProjectName());
                pstmt.setDouble(3, project.getProfitMargin());
                pstmt.setObject(4, project.getStatus().name(), Types.OTHER);
                pstmt.setObject(5, project.getClientId());

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        Project resultProject = new Project();
                        resultProject.setId(rs.getObject("id", UUID.class));
                        resultProject.setProjectName(rs.getString("project_name"));
                        resultProject.setProfitMargin(rs.getDouble("profit_margin"));
                        resultProject.setStatus(ProjectStatus.valueOf(rs.getString("status")));
                        resultProject.setClientId(rs.getObject("client_id", UUID.class));
                        return resultProject;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // TODO: replace null with optional pattern
            return null;
        }

    }
