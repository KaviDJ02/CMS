package live.kavinduj.cms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import live.kavinduj.cms.dao.ComplaintDAO;

import java.io.IOException;

@WebServlet("/adminDashboard")
public class adminDashServlet extends HttpServlet {
    ComplaintDAO complaintDAO;

    @Override
    public void init() throws ServletException {
        complaintDAO = new ComplaintDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logic for handling GET requests to the admin dashboard
        request.setAttribute("complaints", complaintDAO.getAllComplaints());
        request.getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp").forward(request, response);
    }
}
