
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="css/VerifyAccountStyleIndex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <div class="Center">
            <% User acc = (User) session.getAttribute("user");
                if (acc!=null&&!acc.isIsAuthorized()) {
            %>
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
                } else
                    request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
            %>
        </div>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>