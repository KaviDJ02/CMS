<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Employee Dashboard - Complaint Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .logout {
            text-align: right;
            margin-bottom: 20px;
        }
        .logout a {
            color: #d9534f;
            text-decoration: none;
            font-weight: bold;
        }
        .logout a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        textarea, input[type="submit"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        .error {
            color: #d9534f;
            text-align: center;
            margin-bottom: 20px;
        }
        .create-complaint {
            margin: 20px 0;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .create-complaint h3 {
            margin-top: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <%-- Session Validation --%>
    <%
        if (session.getAttribute("username") == null || !"Employee".equals(session.getAttribute("role"))) {
            response.sendRedirect("login");
            return;
        }
    %>

    <div class="logout">
        <p>Welcome, <%= session.getAttribute("username") %> (<%= session.getAttribute("role") %>)</p>
        <a href="logout">Logout</a>
    </div>

    <h2>Employee Dashboard - Manage Your Complaints</h2>

    <%-- Display Error or Success Messages --%>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color: #28a745; text-align: center;">${message}</p>
    </c:if>

    <%-- Create New Complaint Form --%>
    <div class="create-complaint">
        <h3>Create New Complaint</h3>
        <form action="createComplaint" method="post">
            <textarea name="description" placeholder="Enter complaint description" required></textarea>
            <input type="submit" value="Submit Complaint">
        </form>
    </div>

    <%-- Debug Output --%>
    <p>Complaints count: ${complaints.size()}</p>
    <c:if test="${empty complaints}">
        <p class="error">No complaints found.</p>
    </c:if>

    <%-- Complaints Table --%>
        <table>
            <tr>
                <th>ID</th>
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
                    <td>${complaint.description}</td>
                    <td>${complaint.status}</td>
                    <td>${complaint.remarks}</td>
                    <td>${complaint.createdAt}</td>
                    <td>${complaint.updatedAt}</td>
                    <td>
                        <c:if test="${complaint.status ne 'Resolved'}">
                            <form action="updateEmployeeComplaint" method="post" style="display:inline;">
                                <input type="hidden" name="complaintId" value="${complaint.id}">
                                <textarea name="description" placeholder="Update description">${complaint.description}</textarea>
                                <input type="submit" value="Update Description">
                            </form>
                            <form action="deleteEmployeeComplaint" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this complaint?');">
                                <input type="hidden" name="complaintId" value="${complaint.id}">
                                <input type="submit" value="Delete" style="background-color:#d9534f;">
                            </form>
                        </c:if>
                        <c:if test="${complaint.status eq 'Resolved'}">
                            <span style="color:gray;">No actions</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
</div>
</body>
</html>