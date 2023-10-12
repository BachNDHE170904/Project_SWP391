
<%@page import="dal.UserDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
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
            UserDAO db = new UserDAO();
            String avatarLink = db.getUserAvatar(acc.getUserId());
        %>
        <jsp:include page="NavBar.jsp"></jsp:include>
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
                                            <%
                                                if (avatarLink == null || avatarLink.isEmpty()) {
                                            %>
                                            <img class="img-thumbnail" alt="" src="img/default_avatar.jpg" />
                                            <% } else {%>
                                            <img class="img-thumbnail" alt="" src="<%=avatarLink%>" />
                                            <%}%>
                                        </div>
                                        <div class="col-md-9">
                                            <label class="btn btn-outline-primary">
                                                <a class="nav-link active" aria-current="page" href="#"  >Update Profile</a>
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
                                        <label class="form-control mb-1" id="email" value="<%= details.getEmail()%>"><%= details.getEmail()%></label>
                                    </div>
                                    <%if (!acc.isIsAuthorized()) {%>
                                    <div class="alert alert-warning mt-3">
                                        Your email is not confirmed. Please check your inbox.<br>
                                        <button type="button" class="btn btn-primary" onclick="CheckOtp();return false;">
                                            Resend OTP Code
                                        </button><br>
                                        <script>
                                            function CheckOtp() {
                                                let emailInput = document.getElementById("email").innerHTML;
                                                let xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/main/RegisterConfirmAccountServlet");
                                                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                                xhr.send("email=" + emailInput); // Send OTP and user email for validation
                                                const myModal = new bootstrap.Modal(document.getElementById('myModal')).show();
                                            }
                                        </script>
                                        <a href="VerifyAccount.jsp" >Confirm account here</a>
                                    </div>
                                    <%}%>
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
        <!-- Modal -->
        <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Resend Otp</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="txt_field">
                            <label>We have sent an otp code to your email</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Ok</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>