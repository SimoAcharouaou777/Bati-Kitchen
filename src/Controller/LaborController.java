package Controller;

import Model.Labor;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LaborController {
    public static void addLabor(Labor labor){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO labor (name, unit_cost, quantity, hourly_rate, hours_worked, worker_productivity, component_type, vat_rate, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, labor.getName());
            stmt.setDouble(2, labor.getUnitCost());
            stmt.setDouble(3, labor.getQuantity());
            stmt.setDouble(4, labor.getHourlyRate());
            stmt.setDouble(5, labor.getHoursWorked());
            stmt.setDouble(6, labor.getWorkerProductivity());
            stmt.setString(7, labor.getComponentType());
            stmt.setDouble(8, labor.getVatRate());
            stmt.setInt(9, labor.getProjectId());
            stmt.executeUpdate();

    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
