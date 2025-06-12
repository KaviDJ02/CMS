package live.kavinduj.cms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employeeDashboard")
public class employeeDashServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to employeeDashboard.jsp for GET requests
        request.getRequestDispatcher("/WEB-INF/views/employeeDashboard.jsp").forward(request, response);
    }
}
