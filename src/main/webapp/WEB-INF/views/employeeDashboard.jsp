<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("username") == null || !"Employee".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Employee Dashboard</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("username") %></h2>
<a href="logout">Logout</a>
<!-- Add complaint submission form and complaint list here -->
</body>
</html>