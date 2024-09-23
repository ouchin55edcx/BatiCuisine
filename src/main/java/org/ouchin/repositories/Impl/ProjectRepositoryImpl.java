    package org.ouchin.repositories.Impl;

    import org.ouchin.config.DatabaseConfig;
    import org.ouchin.enums.ProjectStatus;
    import org.ouchin.models.Project;
    import org.ouchin.repositories.ProjectRepository;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
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

        @Override
        public List<Project> getAll() {
            List<Project> projects = new ArrayList<>();
            String query = "SELECT * FROM " + tableName;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getObject("id", UUID.class));
                    project.setProjectName(rs.getString("project_name"));
                    project.setProfitMargin(rs.getDouble("profit_margin"));
                    project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
                    project.setClientId(rs.getObject("client_id", UUID.class));
                    projects.add(project);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return projects;
        }

        @Override
        public List<Project> getByClientId(UUID clientId) {
            List<Project> projects = new ArrayList<>();
            String query = "SELECT * FROM " + tableName + " WHERE client_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setObject(1, clientId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Project project = new Project();
                        project.setId(rs.getObject("id", UUID.class));
                        project.setProjectName(rs.getString("project_name"));
                        project.setProfitMargin(rs.getDouble("profit_margin"));
                        project.setStatus(ProjectStatus.valueOf(rs.getString("status")));
                        project.setClientId(rs.getObject("client_id", UUID.class));
                        projects.add(project);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return projects;
        }

    }
