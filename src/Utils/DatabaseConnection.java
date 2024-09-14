package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/bati_cuisine";
    private static final String USER = "bati_user";
    private static final String PASSWORD = "bati_password";
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection(){
        try{
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connected Successfully");
        }catch(SQLException e){
            System.out.println("Connection failed" + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance(){
            if(instance == null){
                instance = new DatabaseConnection();
            }
        return instance;

    }

    public Connection getConnection(){
        return connection;
    }

    public static void closeConnection(Connection connection){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("connection closed");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
