<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - Complaint Management System</title>
    <style>
        .container { width: 300px; margin: 50px auto; }
        .error { color: red; }
        input[type="text"], input[type="password"] { width: 100%; margin: 5px 0; }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="login" method="post" onsubmit="return validateForm()">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <input type="submit" value="Login">
    </form>
</div>
<script>
    function validateForm() {
        let username = document.forms[0]["username"].value;
        let password = document.forms[0]["password"].value;
        if (username.trim() === "" || password.trim() === "") {
            alert("Username and password are required!");
            return false;
        }
        return true;
    }
</script>
</body>
</html>