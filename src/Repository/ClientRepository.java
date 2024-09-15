package Repository;

import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void addClient(String name,String address,String phone,boolean isProfessional) {
        String sql = "INSERT INTO clients(name, address, phone, is_professional) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setBoolean(4, isProfessional);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
