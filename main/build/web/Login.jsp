<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/LoginStyleindex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="WelcomePage.jsp">Happy Programming</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Login.jsp">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Register.jsp">Register</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="Center">
            <h1>Login</h1>
            <form action="LoginServlet" method="POST">
                <!-- Username input -->
                <div class="txt_field">
                    <input type="text" name="email" required /> 
                    <span></span>
                    <label>Email</label>
                </div>
                <!-- Password input -->
                <div class="txt_field">
                    <input type="password" name="password" required />
                    <span></span>
                    <label>Password</label>
                </div>
                <input type="submit" value="Login"/>

                <div class="signup_link">
                    <input type="checkbox" id="rememberPass" name="rememberPass" value="Remember password">
                    <label for="rememberPass"> Remember password</label><br>
                </div>
                <!-- Signup link -->
                <div class="signup_link">
                    Don't have an account?<a href="Register.jsp">Sign up</a>
                </div>
                <div class="signup_link">
                    Forgot your password?<a href="forgotPassword.jsp">Reset here</a>
                </div>
                <%
                    // Server-side code to handle failed login attempt
                    String failedLogin = (String) request.getAttribute("failedLogin");
                    if (failedLogin != null) {
                        if (failedLogin.equalsIgnoreCase("fail")) {
                %> 
                <!-- Display error message for failed login -->
                <div class="WrongLogin">
                    <p>Wrong email or password</p>
                </div>
                <%
                        }
                    }
                %>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>