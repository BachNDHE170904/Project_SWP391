<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="css/RegisterStyleindex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Happy Programming</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="WelcomePage.jsp">Home</a>
                        </li>
                    </ul>
                    <form class="d-flex" action="SearchServlet" method="GET" role="search">
                        <input class="form-control" name="searchResult" type="text" placeholder="Search">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="Login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="Register.jsp">Register</a>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </nav>
        <div class="Center">
            <h1>Register</h1>
            <form action="RegisterServlet" method="POST">
                <div class="container text-center">
                    <div class="row align-items-start">
                        <div class="col">
                            <!-- Username input -->
                            <div class="txt_field">
                                <input type="text" name="username" required /> 
                                <span></span>
                                <label>Username</label>
                            </div>
                            <div class="txt_field">
                                <input type="text" name="email" required /> 
                                <span></span>
                                <label>Email</label>
                            </div>
                            <!-- Password input -->
                            <div class="txt_field">
                                <input type="password" id="password" name="password" required />
                                <span></span>
                                <label>Password</label>
                            </div>
                            <div class="txt_field">
                                <input type="password" id="confirmPassword" name="confirmPassword" required />
                                <span></span>
                                <label>Confirm Password</label>
                            </div>
                            <script>//check if password in confirm password field matches that in password field
                                const password = document.getElementById("password");
                                const passwordConfirm = document.getElementById("confirmPassword");
                                passwordConfirm.addEventListener("change", (event) => {
                                    try {
                                        if (passwordConfirm.value!==password.value) {
                                            alert("Password not matched");
                                            event.target.value = "";
                                        }
                                    } catch (error) {
                                        alert("Password not matched");
                                        event.target.value = "";
                                    }
                                });
                            </script>
                            <div class="txt_field">
                                <input type="text" name="fullname" required />
                                <span></span>
                                <label>Full Name</label>
                            </div>
                        </div>
                        <div class="col">
                            <div class="txt_field">
                                <input type="text" id="phone" name="phone" required />
                                <span></span>
                                <label>Phone number</label>
                            </div>
                            <script>//check phone number
                                const phone = document.getElementById("phone");
                                phone.addEventListener("change", (event) => {
                                    try {
                                        const phoneInt = Number(phone.value);
                                        if (phone.value.length !== 10) {
                                            alert("Phone number have 10 digits.");
                                            event.target.value = "";
                                        }
                                    } catch (error) {
                                        alert("Invalid phone number.");
                                        event.target.value = "";
                                    }
                                });
                            </script>
                            <div class="date_field">
                                <label>Date of Birth:</label> 
                                <input type="date" name="dob" required />
                            </div>
                            <div class="Sex_field">
                                <select class="form-select" name="gender" aria-label="DefaSult select example">
                                    <option selected>Gender</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                            <div class="txt_field">
                                <input type="text" name="address" required />
                                <span></span>
                                <label>Address</label>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="submit" value="Register"/>
                <%
                    // Server-side code to handle failed registration attempt
                    String failedRegister = (String) request.getAttribute("failedRegister");
                    if (failedRegister != null) {
                        if (failedRegister.equalsIgnoreCase("fail")) {
                %> 
                <!-- Display error message for failed registration -->
                <div class="WrongRegister">
                    <p>Account already existed, please choose another username</p>
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