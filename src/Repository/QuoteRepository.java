package Repository;

import Model.Quote;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuoteRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void addQuote(Quote quote){
        String sql = "INSERT INTO quotes (project_id, estimated_amount,issue_date,validity_date,is_accepted) VALUES(?,?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,quote.getProjectId());
            stmt.setDouble(2,quote.getEstimatedAmount());
            stmt.setDate(3,java.sql.Date.valueOf(quote.getIssueDate()));
            stmt.setDate(4,java.sql.Date.valueOf(quote.getValidityDate()));
            stmt.setBoolean(5,quote.isAccepted());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
