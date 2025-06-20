package live.kavinduj.cms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import live.kavinduj.cms.bean.UserDAO;
import live.kavinduj.cms.model.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.authenticate(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            if ("Admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/employeeDashboard");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}