package Repository;

import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public boolean addProject(String name , double profitMargin){
        String sql = "INSERT INTO projects(name,profit_margin,project_status) VALUES(?,?,In Progress)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,name);
            stmt.setDouble(2,profitMargin);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        }catch(SQLException e){
            System.out.println("Failed to insert project:" +e.getMessage());
            return false;
        }
    }
}
