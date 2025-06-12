package live.kavinduj.cms.dao;
import live.kavinduj.cms.model.Complaint;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    private BasicDataSource dataSource;

    public ComplaintDAO() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms_db?useSSL=false");
        dataSource.setUsername("root"); // Replace with your MySQL username
        dataSource.setPassword("1233912"); // Replace with your password
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT c.id, c.user_id, u.username, c.description, c.status, c.remarks, c.created_at, c.updated_at " +
                "FROM complaints c JOIN users u ON c.user_id = u.id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                complaints.add(new Complaint(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("remarks"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaints;
    }

    public boolean updateComplaint(int complaintId, String status, String remarks) {
        String sql = "UPDATE complaints SET status = ?, remarks = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, remarks);
            stmt.setInt(3, complaintId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}