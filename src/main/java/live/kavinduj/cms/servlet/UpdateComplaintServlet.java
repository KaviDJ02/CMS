package live.kavinduj.cms.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.kavinduj.cms.bean.ComplaintDAO;
import java.io.IOException;

@WebServlet("/updateComplaint")
public class UpdateComplaintServlet extends HttpServlet {
    private ComplaintDAO complaintDAO;

    @Override
    public void init() throws ServletException {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is admin
        if (!"Admin".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
        int complaintId = Integer.parseInt(request.getParameter("complaintId"));
        String status = request.getParameter("status");
        String remarks = request.getParameter("remarks");

        if (complaintDAO.updateComplaint(complaintId, status, remarks)) {
            request.setAttribute("message", "Complaint updated successfully");
        } else {
            request.setAttribute("error", "Failed to update complaint");
        }
        // Refresh complaints list
        request.setAttribute("complaints", complaintDAO.getAllComplaints());
        request.getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp").forward(request, response);
    }
}