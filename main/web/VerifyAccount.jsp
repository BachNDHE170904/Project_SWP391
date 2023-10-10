<%@page import="DAL.UserDAO"%>
<!DOCTYPE html>
<html lang="en">
    <%@page import="model.User"%>
    <%@page import="model.UserDetails"%>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="css/VerifyAccountStyleIndex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
        <%
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
            UserDetails details = (UserDetails) session.getAttribute("userDetail");
            UserDAO db = new UserDAO();
        %>
        <nav class="navbar navbar-expand-md bg-body-tertiary ">
            <div class="container-fluid">
                <%if (details.getRoleId() == 1) {%><a class="navbar-brand" href="AdminDashBoard.jsp">Happy Programming</a>
                <%} else {%><a class="navbar-brand" href="WelcomePage.jsp">Happy Programming</a>
                <%}%>
                <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
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
                            <li><a class="dropdown-item" href="ViewUserProfile.jsp">View my Profile</a></li>
                            <li><a class="dropdown-item" href="change.jsp?email=<%= acc.getEmail()%>">Change Password</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="LogOutServlet">Log Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="Center">
            <h1>Verify Account</h1>
            <form action="VerifyAccountServlet" method="POST">
                <div class="txt_field">
                    <input type="password" name="inputOtp" required /> 
                    <span></span>
                    <label>Otp code</label>
                </div>
                <input type="submit" value="Confirm"/>
            </form>
            <%
                // Server-side code to handle failed registration attempt
                String message = (String) request.getAttribute("ms");
                if (message != null) {
            %> 
            <!-- Display error message for failed registration -->
            <div class="WrongOtp">
                <p><%= message%></p>
            </div>
            <%
                }
            %>
        </div>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>