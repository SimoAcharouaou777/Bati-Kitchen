package Repository;

import Model.Project;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void createProject(Project project) {
        try {
            String query = "INSERT INTO projects (name, profit_margin, total_cost,client_id,project_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDouble(2, project.getProfitMargin());
            preparedStatement.setDouble(3, project.getTotalCost());
            preparedStatement.setInt(4, project.getClientId().getClientId());
            preparedStatement.setString(5, project.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
