package live.kavinduj.cms.model;
import java.sql.Timestamp;

public class Complaint {
    private int id;
    private int userId;
    private String username; // For display
    private String description;
    private String status;
    private String remarks;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor
    public Complaint(int id, int userId, String username, String description, String status, String remarks, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.status = status;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Complaint(int id, int userId, String description, String status, String remarks, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.status = status;
        this.remarks = remarks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getRemarks() { return remarks; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
}