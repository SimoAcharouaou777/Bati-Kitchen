package Controller;

import Model.Material;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static void updateMaterialTVA(Material material){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "UPDATE material SET vat_rate = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDouble(1,material.getVatRate());
            preparedStatement.setInt(2,material.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Material> findMaterialByProjectId(int projectId){
            Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material WHERE project_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Material material = new Material(
                        resultSet.getString("name"),
                        resultSet.getDouble("unit_cost"),
                        resultSet.getDouble("quantity"),
                        resultSet.getDouble("vat_rate"),
                        resultSet.getInt("project_id"),
                        resultSet.getDouble("transport_cost"),
                        resultSet.getDouble("quality_coefficient")
                );
                material.setId(resultSet.getInt("id"));
                materials.add(material);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return materials;
    }
}
