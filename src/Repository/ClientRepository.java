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

    public boolean clientExists(int clientId){
        String sql = "SELECT COUNT(*) FROM clients WHERE client_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, clientId);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Client getClientByName(String name){
        String sql = "SELECT * FROM clients WHERE name = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Client client = new Client(rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getBoolean("is_professional"));
                    client.setClientId(rs.getInt("client_id"));
                    return client;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
