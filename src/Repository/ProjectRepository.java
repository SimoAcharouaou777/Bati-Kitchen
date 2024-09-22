package Repository;

import Controller.ClientController;
import Model.Client;
import Model.Project;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void createProject(Project project) {
        try {
            String query = "INSERT INTO projects (name, profit_margin, total_cost, client_id, project_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDouble(2, project.getProfitMargin());
            preparedStatement.setDouble(3, project.getTotalCost());
            preparedStatement.setInt(4, project.getClientId().getClientId());
            preparedStatement.setString(5, project.getStatus().name());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    project.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectProfitMargin(int projectId, double profitMargin) {
        String query = "UPDATE projects SET profit_margin = ? WHERE project_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, profitMargin);
            preparedStatement.setInt(2, projectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Project project = new Project( rs.getString("name"), ClientController.getClientById(rs.getInt("client_id")));
                project.setId(rs.getInt("project_id"));
                project.setName(rs.getString("name"));
                project.setProfitMargin(rs.getDouble("profit_margin"));
                project.setTotalCost(rs.getDouble("total_cost"));
                int clientId = rs.getInt("client_id");
                Client client = ClientController.getClientById(clientId);
                project.setClientId(client);
                project.setStatus(Project.ProjectStatus.valueOf(rs.getString("project_status")));
                projects.add(project);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}