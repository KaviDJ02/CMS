<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - Complaint Management System</title>
    <style>
        /* General reset and base styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Arial, sans-serif;
        }

        body {
            background-color: #f5f7fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        /* Container styling */
        .container {
            background-color: #ffffff;
            padding: 2rem 3rem;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        /* Heading styling */
        h2 {
            font-size: 1.8rem;
            color: #1a73e8;
            margin-bottom: 1.5rem;
            font-weight: 600;
        }

        /* Form styling */
        form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        /* Label styling */
        label {
            font-size: 1rem;
            color: #444;
            text-align: left;
            font-weight: 500;
        }

        /* Input field styling */
        input[type="text"],
        input[type="password"] {
            padding: 0.75rem;
            border: 1px solid #dfe1e5;
            border-radius: 8px;
            font-size: 1rem;
            color: #333;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #1a73e8;
            box-shadow: 0 0 8px rgba(26, 115, 232, 0.2);
        }

        /* Submit button styling */
        input[type="submit"] {
            background-color: #1a73e8;
            color: #ffffff;
            padding: 0.75rem;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        input[type="submit"]:hover {
            background-color: #1557b0;
            transform: translateY(-2px);
        }

        input[type="submit"]:active {
            transform: translateY(0);
        }

        /* Error message styling */
        .error {
            color: #d32f2f;
            font-size: 0.9rem;
            margin-bottom: 1rem;
            background-color: #fee2e2;
            padding: 0.5rem;
            border-radius: 6px;
        }

        /* Responsive design */
        @media (max-width: 480px) {
            .container {
                padding: 1.5rem;
                margin: 1rem;
            }

            h2 {
                font-size: 1.5rem;
            }

            input[type="text"],
            input[type="password"],
            input[type="submit"] {
                font-size: 0.9rem;
            }
        }
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