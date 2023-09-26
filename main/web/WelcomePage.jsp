<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="model.User"%>
<%@page import="model.UserDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <link rel="stylesheet" href="css/WelcomePageStyleIndex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
                            <% if (acc != null) { %>
                            <li><a class="dropdown-item" href="ViewUserProfile.jsp">View my Profile</a></li>
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



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
