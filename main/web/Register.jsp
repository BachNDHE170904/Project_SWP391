
<%@page import="model.UserDetails"%>
<%@page import="model.SendEmail"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up Page</title>
        <link rel="stylesheet" href="css/RegisterStyleindex.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <form action="RegisterServlet" id="signupForm" method="POST" onsubmit="return printmsg()">
                <div class="Center">
                    <h1>Sign up</h1>
                    <div class="container text-center">
                        <div class="row align-items-start">
                            <div class="col">
                                <!-- Username input -->
                                <div class="txt_field">
                                    <input type="text" id="username" name="username"<c:if test="${ requestScope.details.username!=null }">value="${requestScope.details.username}"</c:if>  required /> 
                                    <span></span>
                                    <label>Username</label>
                                </div>
                                <div class="txt_field">
                                    <input type="text"id="email" name="email"<c:if test="${ requestScope.details.email!=null }">value="${requestScope.details.email}"</c:if> required /> 
                                    <span></span>
                                    <label>Email</label>
                                </div>
                                <script>
                                    function isEmailValid(email) {
                                        // Regular expression pattern for a valid email address
                                        let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                                        return emailPattern.test(email);
                                    }
                                    let emailInput = document.getElementById("email");
                                    emailInput.addEventListener("change", (event) => {
                                        let email = emailInput.value.trim();
                                        if (isEmailValid(email)) {
                                            emailValidationMessage.textContent = "Email is valid.";
                                            emailValidationMessage.style.color = "green";
                                        } else {
                                            alert("Invalid Email");
                                            event.target.value = "";
                                        }
                                    });
                                </script>
                                <!-- Password input -->
                                <div class="txt_field">
                                    <input type="password" id="password" name="password" <c:if test="${ requestScope.details.pass!=null }">value="${requestScope.details.pass}"</c:if>required>
                                    <span></span>
                                    <label>Password</label>
                                </div>
                                <script>
                                    // Get a reference to the password input field and the error message span
                                    let passwordInput = document.getElementById("password");
                                    // Add an event listener to the input field to validate the password
                                    passwordInput.addEventListener("change", (event) => {
                                        const password = passwordInput.value;

                                        // Define the regular expression pattern for password validation
                                        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

                                        // Check if the password matches the pattern
                                        if (!passwordPattern.test(password)) {
                                            alert("Password must contain at least eight characters, at least one letter, one number and one special character.");
                                            event.target.value = "";
                                        }
                                    });
                                </script>
                                <div class="txt_field">
                                    <input type="password" id="confirmPassword" name="confirmPassword"<c:if test="${ requestScope.details.pass!=null }">value="${requestScope.details.pass}"</c:if> required />
                                    <span></span>
                                    <label>Confirm Password</label>
                                </div>
                                <script>//check if password in confirm password field matches that in password field
                                    let password = document.getElementById("password");
                                    let passwordConfirm = document.getElementById("confirmPassword");
                                    passwordConfirm.addEventListener("change", (event) => {
                                        try {
                                            if (passwordConfirm.value !== password.value) {
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
                                    <input type="text" name="fullname" <c:if test="${ requestScope.details.fullname!=null }">value="${requestScope.details.fullname}"</c:if>required />
                                    <span></span>
                                    <label>Full Name</label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="txt_field">
                                    <input type="text" id="phone" name="phone"<c:if test="${ requestScope.details.phone!=null }">value="${requestScope.details.phone}"</c:if> required />
                                    <span></span>
                                    <label>Phone number</label>
                                </div>
                                <script>//check phone number
                                    let phone = document.getElementById("phone");
                                    phone.addEventListener("change", (event) => {
                                        try {
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
                                    <input type="date"id="dob" name="dob"<c:if test="${ requestScope.details.dob!=null }">value="${requestScope.details.dob}"</c:if> required />
                                </div>
                                <script>//check dob
                                    let dob = document.getElementById("dob");
                                    dob.addEventListener("blur", () => {
                                        try {
                                            let today = new Date();
                                            let birthDate = new Date(dob.value);
                                            let age = today.getFullYear() - birthDate.getFullYear();
                                            let monthDiff = today.getMonth() - birthDate.getMonth();

                                            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                                                age--;
                                            }
                                            if (age > 130) {
                                                alert("User must be less than 130 years old");
                                                event.target.value = "";
                                            }
                                            if (age < 18) {
                                                alert("User must be over 17 years old");
                                                event.target.value = "";
                                            }
                                        } catch (error) {
                                            alert("Invalid dob.");
                                            event.target.value = "";
                                        }
                                    });
                                </script>
                                <div class="Sex_field">
                                    <select class="form-select" name="gender" aria-label="DefaSult select example">
                                        <option value="Male"<c:if test="${ requestScope.details.sex!=null&&requestScope.details.sex==true }">selected</c:if> >Male</option>
                                    <option value="Female"<c:if test="${ requestScope.details.sex!=null&&requestScope.details.sex==false }">selected</c:if>>Female</option>
                                    </select>
                                </div>
                                <div class="txt_field">
                                    <input type="text" name="address" <c:if test="${ requestScope.details.address!=null }">value="${requestScope.details.address}"</c:if>required />
                                    <span></span>
                                    <label>Address</label>
                                </div>
                            </div>
                        </div>
                    </div>
                <%
                    // Server-side code to handle failed registration attempt
                    String message = (String) request.getAttribute("ms");
                    if (message != null) {
                %> 
                <!-- Display error message for failed registration -->
                <div class="WrongRegister">
                    <p><%= message%></p>
                </div>
                <%
                    }
                %>
                <!-- Button trigger modal -->
                <button type="submit"id="signup_link" class="signup_link" >
                    Sign Up
                </button>
            </div>
        </form>
        <script>
            function printmsg() {
                if (!form.checkValidity()) {
                    alert("Please fill in all required fields.");
                    return false;
                } else {
                    let emailInput = document.getElementById("email").value; // Get the email input value
                    let xhr = new XMLHttpRequest();
                    xhr.open("POST", "/main/RegisterConfirmAccountServlet");
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.send("email=" + emailInput); // Send OTP and user email for validation
                    return true;
                }
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>