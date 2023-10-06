<%-- 
    Document   : change
    Created on : Sep 18, 2023, 1:04:12 AM
    Author     : admin
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                text-align: center;
            }

            h1 {
                color: #333;
            }

            h3 {
                color: red;
            }

            form {
                background-color: #fff;
                border: 1px solid #ccc;
                padding: 20px;
                max-width: 50%;
                margin: 0 auto;
            }

            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <%
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
        %>
        <nav class="navbar navbar-expand-md bg-body-tertiary ">
            <div class="container-fluid">
                <a class="navbar-brand" href="WelcomePage.jsp">Happy Programming</a>
                <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <%
                        if (acc != null) {
                    %>
                    <div class="nav-item dropdown ms-auto">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <%= acc.getUsername()%>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <% if (acc != null) {%>
                            <li><a class="dropdown-item" href="ViewUserProfile.jsp">View my Profile</a></li>
                            <li><a class="dropdown-item" href="change.jsp?email=<%= acc.getEmail()%>">Change Password</a></li>
                                <%}%>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="LogOutServlet">Log Out</a></li>
                        </ul>
                    </div>
                    <%
                    } else {
                    %>
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Login.jsp">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Register.jsp">Register</a>
                        </li>
                    </ul>
                    <%
                        }
                    %>
                </div>
            </div>
        </nav>
        <input type="hidden" id="status" value="<%=request.getAttribute("status")%>}"/>
        <h1>Change password</h1>
        <h3 style="color:red">${sessionScope.ms}</h3>
        <form action="changePasswordServlet" method="GET">
            <div class="col-md-6 offset-md-3">
                <span class="anchor" id="formChangePassword"></span>
                <hr class="mb-5">

                <!-- form card change password -->
                <div class="card card-outline-secondary">
                    <div class="card-header">
                        <h3 class="mb-0">Change Password</h3>
                    </div>
                    <div class="card-body">
                        <div class="form" role="form" autocomplete="off">
                            <div class="form-group">
                                <label for="inputPasswordOld">Current Password</label>
                                <input type="password" class="form-control" id="inputPasswordOld" required="">
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordNew">New Password</label>
                                <input type="password" class="form-control" id="inputPasswordNew" required="">
                                <span class="form-text small text-muted">
                                        The password must be 8-20 characters, and must <em>not</em> contain spaces.
                                    </span>
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordNewVerify">Verify</label>
                                <input type="password" class="form-control" id="inputPasswordNewVerify" required="">
                                <span class="form-text small text-muted">
                                        To confirm, type the new password again.
                                    </span>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-lg float-right">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <%
            // Server-side code to handle failed registration attempt
            String message = (String) request.getAttribute("ms");
            if (message != null) {
        %> 
        <!-- Display error message for failed registration -->
        <p><%= message%></p>
        <%
            }
        %>
    </body>
</html>
