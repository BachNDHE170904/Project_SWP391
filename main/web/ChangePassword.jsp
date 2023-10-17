<%-- 
    Document   : change
    Created on : Sep 18, 2023, 1:04:12 AM
    Author     : admin
--%>
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
        <jsp:include page="NavBar.jsp"></jsp:include>
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
