<!DOCTYPE html>
<html lang="en">
    <%@page import="model.User"%>
    <%@page import="model.UserDetails"%>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="ViewProfileStyleIndex.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
        <%
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
            UserDetails details = (UserDetails) session.getAttribute("userDetail");
        %>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Happy Programming</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <%
                        if (acc != null) {
                    %>
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <%= acc.getUsername()%>
                        </a>
                        <ul class="dropdown-menu">
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
                    <ul class="navbar-nav">
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
        <div class="container light-style flex-grow-1 container-p-y">
            <h4 class="font-weight-bold py-3 mb-4">
                Account Profile
            </h4>
            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <a class="list-group-item list-group-item-action active" data-toggle="list"
                               href="#account-general">General</a>
                            <a class="list-group-item list-group-item-action" data-toggle="list"
                               href="#account-info">Info</a>

                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <div class="tab-pane fade active show" id="account-general">
                                <div class=container text-center"">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <img src="images/default_avatar.png" alt class="img-fluid" width="200" height="200">
                                        </div>
                                        <div class="col-md-9">
                                            <label class="btn btn-outline-primary">
                                                <a class="nav-link active" aria-current="page" href="#">Update Profile</a>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <hr class="border-light m-0">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="form-label">Username</label>
                                        <label class="form-control mb-1"><%= details.getUsername()%></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Full Name</label>
                                        <label class="form-control mb-1"><%= details.getFullname()%></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">E-mail</label>
                                        <label class="form-control mb-1"><%= details.getEmail()%></label>
                                        <div class="alert alert-warning mt-3">
                                            Your email is not confirmed. Please check your inbox.<br>
                                            <a href="javascript:void(0)">Resend confirmation</a>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="tab-pane fade" id="account-info">
                                <div class="card-body pb-2">
                                    <div class="form-group">
                                        <label class="form-label">Birthday</label>
                                        <label class="form-control mb-1"> <%= details.getDob()%> </label>
                                    </div>
                                    <div class="form-group">
                                        <%
                                            boolean sex = details.isSex();
                                            String sexName;
                                            if (sex == true)
                                                sexName = "male";
                                            else
                                                sexName = "female";
                                        %>
                                        <label class="form-label">Sex</label>
                                        <label class="form-control mb-1"><%=  sexName%></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Address</label>
                                        <label class="form-control mb-1"><%= details.getAddress()%></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Phone</label>
                                        <label class="form-control mb-1"><%= details.getPhone()%></label>
                                    </div>
                                </div>
                            </div>

                            <hr class="border-light m-0">

                        </div>
                    </div>
                </div>


            </div>

        </div>

    </div>
    <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">

    </script>
</body>

</html>