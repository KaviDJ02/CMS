package live.kavinduj.cms.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.kavinduj.cms.dao.ComplaintDAO;
import java.io.IOException;

@WebServlet("/createComplaint")
public class CreateComplaintServlet extends HttpServlet {
    private ComplaintDAO complaintDAO;

    @Override
    public void init() throws ServletException {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is employee
        if (!"Employee".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
        int userId = (Integer) request.getSession().getAttribute("userId");
        String description = request.getParameter("description");

        if (complaintDAO.createComplaint(userId, description)) {
            request.setAttribute("message", "Complaint created successfully");
        } else {
            request.setAttribute("error", "Failed to create complaint");
        }
        // Refresh complaints list
        request.setAttribute("complaints", complaintDAO.getComplaintsByEmployeeId(userId));
        request.getRequestDispatcher("/WEB-INF/views/employeeDashboard.jsp").forward(request, response);
    }
}