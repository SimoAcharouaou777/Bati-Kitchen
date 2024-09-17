package Controller;

import Model.Material;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MaterialController {
    public static void addMaterial(Material material){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO material (name, unit_cost, quantity, transport_cost, quality_coefficient, component_type, vat_rate, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, material.getName());
            stmt.setDouble(2, material.getUnitCost());
            stmt.setDouble(3, material.getQuantity());
            stmt.setDouble(4, material.getTransportCost());
            stmt.setDouble(5, material.getQualityCoefficient());
            stmt.setString(6, material.getComponentType());
            stmt.setDouble(7, material.getVatRate());
            stmt.setInt(8, material.getProjectId());
            stmt.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
