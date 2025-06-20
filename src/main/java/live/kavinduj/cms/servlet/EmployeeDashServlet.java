package live.kavinduj.cms.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.kavinduj.cms.bean.ComplaintDAO;
import java.io.IOException;

@WebServlet("/employeeDashboard")
public class EmployeeDashServlet extends HttpServlet {
    private ComplaintDAO complaintDAO;

    @Override
    public void init() throws ServletException {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is employee
        if (!"Employee".equals(request.getSession().getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
        // Fetch employee's complaints
        int userId = (Integer) request.getSession().getAttribute("userId");
        request.setAttribute("complaints", complaintDAO.getComplaintsByEmployeeId(userId));
        request.getRequestDispatcher("/WEB-INF/views/employeeDashboard.jsp").forward(request, response);
    }
}