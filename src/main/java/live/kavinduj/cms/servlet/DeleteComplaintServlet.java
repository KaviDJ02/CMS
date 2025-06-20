package live.kavinduj.cms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.kavinduj.cms.bean.ComplaintDAO;
import java.io.IOException;

@WebServlet("/deleteComplaint")
public class DeleteComplaintServlet extends HttpServlet {
    private ComplaintDAO complaintDAO;

    @Override
    public void init() throws ServletException {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Only allow admin
        if (!"Admin".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
        int complaintId = Integer.parseInt(request.getParameter("complaintId"));
        boolean deleted = complaintDAO.deleteComplaintById(complaintId);

        if (deleted) {
            request.setAttribute("message", "Complaint deleted successfully");
        } else {
            request.setAttribute("error", "Failed to delete complaint");
        }
        request.setAttribute("complaints", complaintDAO.getAllComplaints());
        request.getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp").forward(request, response);
    }
}