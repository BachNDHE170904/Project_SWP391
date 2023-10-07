
<%@page import="DAL.UserDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>DASHMIN - Bootstrap Admin Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/AdminDashBoardStyleIndex.css" rel="stylesheet">
    </head>

    <body>
        <%
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
            UserDetails details = (UserDetails) session.getAttribute("userDetail");
            UserDAO db = new UserDAO();
            if (acc!=null&&details.getRoleId() == 1) {
        %>
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!-- Sidebar Start -->
            <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <%
                                String avatarLink = db.getUserAvatar(acc.getUserId());
                                if (avatarLink == null || avatarLink.isEmpty()) {
                            %>
                            <img class="rounded-circle" alt="" src="img/default_avatar.jpg" style="width: 40px; height: 40px;" />
                            <% } else {%>
                            <img class="rounded-circle" alt="" src="<%=avatarLink%>" style="width: 40px; height: 40px;" />
                            <%}%>
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0">Admin</h6>
                            <span>Admin</span>
                        </div>
                    </div>
                    <div class="navbar-nav w-100">
                        <a href="AdminDashBoard.jsp" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>
                        <a href="#" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Mangage Users</a>
                        <a href="#" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>Manage Skills</a>
                        <a href="#" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Manage Mentors</a>
                        <a href="#" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Manage Requests</a>
                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand-md bg-body-tertiary ">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="AdminDashBoard.jsp">Happy Programming</a>
                        <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse " id="navbarSupportedContent">
                            <div class="nav-item dropdown ms-auto">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%
                                        avatarLink = db.getUserAvatar(acc.getUserId());
                                        if (avatarLink == null || avatarLink.isEmpty()) {
                                    %>
                                    <img class="rounded-circle" alt="" src="img/default_avatar.jpg" style="width: 40px; height: 40px;" />
                                    <% } else {%>
                                    <img class="rounded-circle" alt="" src="<%=avatarLink%>" style="width: 40px; height: 40px;" />
                                    <%}%>
                                    <%= acc.getUsername()%>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <% if (acc != null) {%>
                                    <li><a class="dropdown-item" href="ViewUserProfile.jsp">View my Profile</a></li>
                                    <li><a class="dropdown-item" href="change.jsp">Change Password</a></li>
                                        <%}%>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="LogOutServlet">Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->
                <!-- Content End -->


                <!-- Back to Top -->
                <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
            </div>
            <%
                } else
                    request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
            %>
            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="lib/chart/chart.min.js"></script>
            <script src="lib/easing/easing.min.js"></script>
            <script src="lib/waypoints/waypoints.min.js"></script>
            <script src="lib/owlcarousel/owl.carousel.min.js"></script>
            <script src="lib/tempusdominus/js/moment.min.js"></script>
            <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
            <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

            <!-- Template Javascript -->
            <script src="js/main.js"></script>
    </body>

</html>