<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("username") == null || !"Admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("username") %></h2>
<a href="logout">Logout</a>
<!-- Add complaint management features here -->
</body>
</html>