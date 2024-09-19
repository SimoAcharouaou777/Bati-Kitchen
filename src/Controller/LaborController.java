package Controller;

import Model.Labor;
import Model.Material;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaborController {
    public static void addLabor(Labor labor){
          Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO labor (name, hourly_rate, hours_worked, worker_productivity, component_type, vat_rate, project_id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, labor.getName());
            stmt.setDouble(2, labor.getHourlyRate());
            stmt.setDouble(3, labor.getHoursWorked());
            stmt.setDouble(4, labor.getWorkerProductivity());
            stmt.setString(5, labor.getComponentType());
            stmt.setDouble(6, labor.getVatRate());
            stmt.setInt(7, labor.getProjectId());
            stmt.executeUpdate();

    }catch (Exception e){
        e.printStackTrace();
    }
    }

    public static void updateLaborTVA(Labor labor){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "UPDATE labor SET vat_rate = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDouble(1,labor.getVatRate());
            preparedStatement.setInt(2,labor.getId());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<Labor> findLaborByProjectId(int projectId){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Labor> labors = new ArrayList<>();
        String query = "SELECT * FROM labor WHERE project_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Labor labor = new Labor(
                        resultSet.getString("name"),
                        resultSet.getDouble("hourly_rate"),
                        resultSet.getInt("hours_worked"),
                        resultSet.getDouble("vat_rate"),
                        resultSet.getInt("project_id"),
                        resultSet.getDouble("worker_productivity")
                );
                labor.setId(resultSet.getInt("id"));
                labors.add(labor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return labors;
    }


}
