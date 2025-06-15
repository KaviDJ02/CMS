<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Admin Dashboard - Complaint Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }
        .container { max-width: 1200px; margin: 0 auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { color: #333; text-align: center; }
        .logout { text-align: right; margin-bottom: 20px; }
        .logout a { color: #d9534f; text-decoration: none; font-weight: bold; }
        .logout a:hover { text-decoration: underline; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        tr:hover { background-color: #f1f1f1; }
        select, textarea, input[type="submit"] { width: 100%; padding: 8px; margin: 5px 0; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background-color: #28a745; color: white; cursor: pointer; }
        input[type="submit"]:hover { background-color: #218838; }
        .error { color: #d9534f; text-align: center; margin-bottom: 20px; }
    </style>
</head>
<body>
<div class="container">
    <%-- Session Validation --%>
    <%
        if (session.getAttribute("username") == null || !"Admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
    %>

    <div class="logout">
        <p>Welcome, <%= session.getAttribute("username") %> (<%= session.getAttribute("role") %>)</p>
        <a href="logout">Logout</a>
    </div>

    <h2>Admin Dashboard - Manage Complaints</h2>

    <%-- Debug Output --%>
    <p>Complaints count: ${complaints.size()}</p>
    <c:if test="${empty complaints}">
        <p class="error">No complaints found.</p>
    </c:if>

    <%-- Complaints Table --%>
    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remarks</th>
            <th>Created At</th>
            <th>Updated At</th>
            <th>Action</th>
        </tr>
        <c:forEach var="complaint" items="${complaints}">
            <tr>
                <td>${complaint.id}</td>
                <td>${complaint.username}</td>
                <td>${complaint.description}</td>
                <td>${complaint.status}</td>
                <td>${complaint.remarks}</td>
                <td>${complaint.createdAt}</td>
                <td>${complaint.updatedAt}</td>
                <td>
                    <form action="updateComplaint" method="post">
                        <input type="hidden" name="complaintId" value="${complaint.id}">
                        <select name="status" required>
                            <option value="Pending" ${complaint.status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="In-Progress" ${complaint.status == 'In-Progress' ? 'selected' : ''}>In-Progress</option>
                            <option value="Resolved" ${complaint.status == 'Resolved' ? 'selected' : ''}>Resolved</option>
                        </select>
                        <textarea name="remarks" placeholder="Add remarks">${complaint.remarks}</textarea>
                        <input type="submit" value="Update">
                    </form>
                    <form action="deleteComplaint" method="post" style="margin-top:5px;" onsubmit="return confirm('Are you sure you want to delete this complaint?');">
                        <input type="hidden" name="complaintId" value="${complaint.id}">
                        <input type="submit" value="Delete" style="background-color:#d9534f; color:white;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>