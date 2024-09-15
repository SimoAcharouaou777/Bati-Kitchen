package Repository;

import Model.Client;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void addClient(Client client) {
        String sql = "INSERT INTO clients(name, address, phone, is_professional) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhone());
            stmt.setBoolean(4, client.isProfessional());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllClients() {
        String sql = "SELECT * FROM clients";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Client ID : " + rs.getInt("client_id"));
                System.out.println("Client Name : " + rs.getString("name"));
                System.out.println("Client Address : " + rs.getString("address"));
                System.out.println("Client Phone : " + rs.getString("phone"));
                System.out.println("Is Professional : " + rs.getBoolean("is_professional"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
