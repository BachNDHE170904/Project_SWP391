<%-- 
    Document   : change
    Created on : Sep 18, 2023, 1:04:12 AM
    Author     : admin
--%>

<%@page import="DAL.UserDAO"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link rel="stylesheet" href="css/LoginStyleindex.css">
    </head>
    <body>
        <%
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
            UserDAO db = new UserDAO();
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
                            <%
                                String avatarLink = db.getUserAvatar(acc.getUserId());
                                if (avatarLink == null || avatarLink.isEmpty()) {
                            %>
                            <img class="rounded-circle" alt="" src="img/default_avatar.jpg" style="width: 40px; height: 40px;" />
                            <% } else {%>
                            <img class="rounded-circle" alt="" src="<%=avatarLink%>" style="width: 40px; height: 40px;" />
                            <%}%>
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
        <div class="Center">
            <h1>Change Password</h1>
            <form action="changePasswordServlet" method="GET">
                <!-- Username input -->
                <div class="txt_field">
                    <input type="password" name="opass" required /> 
                    <span></span>
                    <label>Current Password</label>
                    
                </div>
                <!-- Password input -->
                <div class="txt_field">
                    <input type="password" name="pass" required />
                    <span></span>
                    <label>New Password</label>
                </div>
                <div class="txt_field">
                    <input type="password" name="rpass" required />
                    <span></span>
                    <label>Confirm Password</label>
                </div>
                <%
                        String message = (String) request.getAttribute("message");
                        
                    %>
                    <%if (message != null) {%>
                    <p class="error"><%= message%></p>
                        <%
                            }
                        %>
                <input type="submit" value="Change"/>
            </form>
        </div>
        
        <!-- Display error message for failed registration -->
                    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        
    </body>
</html>
