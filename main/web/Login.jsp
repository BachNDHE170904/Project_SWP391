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
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body onload="generate()">
        <%
            Cookie[] cookies = request.getCookies();
            String rmbEmail = "", rmbPass = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("email")) {
                        rmbEmail = cookie.getValue();
                    } else if (cookie.getName().equals("password")) {
                        rmbPass = cookie.getValue();
                    }
                }
            }
            String msg = (String) session.getAttribute("status");
            if (msg != null) {%>

        <script>
            swal("Congrats", "<%= msg%>", "success");
        </script>

        <% session.removeAttribute("status");
            }%>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <div class="Center">
                <h1>Login</h1>
                <form action="LoginServlet" method="POST" onsubmit="return printmsg();">
                    <!-- Username input -->
                    <div class="txt_field">
                        <input type="text" name="email" value="<%= rmbEmail%>"required /> 
                    <span></span>
                    <label>Email</label>
                </div>
                <!-- Password input -->
                <div class="txt_field">
                    <input type="password" name="password" value="<%= rmbPass%>" required />
                    <span></span>
                    <label>Password</label>
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
                    <p id="key"></p>
                </div>
                <%
                        }
                    }
                %>
                <div class="WrongLogin">
                    <p id="key"></p>
                </div>
                <div id="captcha-input" class="inline">
                    <input type="text" id="captcha" placeholder="Captcha code" required/>
                </div>

                <div id="image"
                     class="inline"
                     selectable="False">
                </div>
                <input type="submit" value="Login" id="btn" />

                <div class="signup_link">
                    <input type="checkbox" id="rememberPass" name="rememberPass" value="true">
                    <label for="rememberPass"> Remember password</label><br>
                </div>
                <!-- Signup link -->
                <div class="signup_link">
                    Don't have an account?<a href="Register.jsp">Sign up</a>
                </div>
                <div class="signup_link">
                    Forgot your password?<a href="ForgotPassword.jsp">Reset here</a>
                </div>

            </form>
        </div>
        <script>
            let captcha;
            function generate() {

                // Clear old input
                document.getElementById("captcha").value = "";

                // Access the element to store
                // the generated captcha
                captcha = document.getElementById("image");
                let uniquechar = "";

                const randomchar =
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

                // Generate captcha for length of
                // 5 with random character
                for (let i = 1; i < 5; i++) {
                    uniquechar += randomchar.charAt(
                            Math.random() * randomchar.length);
                }

                // Store generated input
                captcha.innerHTML = uniquechar;
            }

            function printmsg() {
                const usr_input = document.getElementById("captcha").value;
                // Check whether the input is equal
                // to generated captcha or not
                if (usr_input !== captcha.innerHTML) {
                    let s = document.getElementById("key").innerHTML = "Wrong captcha";
                    return false;
                }
                else{
                    return true;
                }
            }

        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>