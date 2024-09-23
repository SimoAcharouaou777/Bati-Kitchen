package Repository;

import Model.Quote;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuoteRepository {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    public void addQuote(Quote quote){
        String sql = "INSERT INTO quotes (project_id, estimated_amount,issue_date,validity_date,is_accepted) VALUES(?,?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,quote.getProjectId());
            stmt.setDouble(2,quote.getEstimatedAmount());
            stmt.setDate(3,java.sql.Date.valueOf(quote.getIssueDate()));
            if(quote.getValidityDate() != null ){
                stmt.setDate(4,java.sql.Date.valueOf(quote.getValidityDate()));
            }else{
                stmt.setNull(4,java.sql.Types.DATE);
            }
            stmt.setBoolean(5,quote.isAccepted());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Quote getQuoteByProjectId(int projectId){
        String sql = "SELECT * FROM quotes WHERE project_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,projectId);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Quote quote = new Quote(
                    rs.getInt("project_id"),
                    rs.getDouble("estimated_amount"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("validity_date").toLocalDate(),
                    rs.getBoolean("is_accepted")
                    );
                    return quote;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
