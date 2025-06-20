package live.kavinduj.cms.bean;
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
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms_db?useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root"); // Replace with your MySQL username
        dataSource.setPassword("1233912"); // Replace with your password
    }

    // Get complaints for a specific employee
    public List<Complaint> getComplaintsByEmployeeId(int userId) {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT id, user_id, description, status, remarks, created_at, updated_at " +
                "FROM complaints WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                complaints.add(new Complaint(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("remarks"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error in getComplaintsByEmployeeId: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Retrieved " + complaints.size() + " complaints for user " + userId);
        return complaints;
    }

    // Create a new complaint
    public boolean createComplaint(int userId, String description) {
        String sql = "INSERT INTO complaints (user_id, description, status, created_at, updated_at) " +
                "VALUES (?, ?, 'Pending', NOW(), NOW())";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, description);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creating complaint: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Update complaint description
    public boolean updateComplaintDescription(int complaintId, String description) {
        String sql = "UPDATE complaints SET description = ?, updated_at = NOW() WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, description);
            stmt.setInt(2, complaintId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error updating complaint description: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Reinclude getAllComplaints() for AdminDashboardServlet
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

                        getUsernameFormId(rs.getInt("user_id")), // Fetch username from user ID

                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("remarks"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ));
            }

        } catch (Exception e) {
            System.err.println("Error in getAllComplaints: " + e.getMessage());
            e.printStackTrace();
        }
        return complaints;
    }

    public boolean updateComplaint(int complaintId, String status, String remarks) {
        String sql = "UPDATE complaints SET status = ?, remarks = ?, updated_at = NOW() WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, remarks);
            stmt.setInt(3, complaintId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error updating complaint: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComplaintByEmployee(int complaintId, int userId) {
        String sql = "DELETE FROM complaints WHERE id = ? AND user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, complaintId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error deleting complaint: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String getUsernameFormId (int userId) {
        String sql = "SELECT username FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            System.err.println("Error getting username: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteComplaintById(int complaintId) {
        String sql = "DELETE FROM complaints WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, complaintId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error deleting complaint by ID: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}