
<%@page import="controller.Constants"%>
<%@page import="model.Skill"%>
<%@page import="model.Skill"%>
<%@page import="dal.SkillDAO"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>DashBoard</title>
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
            if (acc != null && details.getRoleId() == 1) {
        %>
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!-- Sidebar Start -->
            <jsp:include page="DashBoardSideBar.jsp"></jsp:include>
                <!-- Sidebar End -->

                <!-- Content Start -->
                <div class="content">
                    <!-- Navbar Start -->
                <jsp:include page="NavBar.jsp"></jsp:include>
                    <!-- Navbar End -->

                    <!-- Table Start -->
                   
                    <div class="container-fluid pt-4 px-4">
                        <div class="row g-4">
                            <div class="col-12">
                                <div class="bg-light rounded h-100 p-4">
                                    <h6 class="mb-4">Manage users</h6>
                                    <div class="inner-form">
                                        <div class="input-field">
                                            <input class="form-control" id="choices-text-preset-values" type="text" placeholder="Type to search..." />
                                            <button class="btn-search" type="button">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                    
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Full Name</th>
                                                    <th scope="col">Account Name</th>
                                                    <th scope="col">Role</th>
                                                    <th scope="col">Number of currently requests</th>
                                                    <th scope="col">Status</th>
                                                    <th scope="col">Enable/Disable</th>
                                                   
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                UserDAO userDb = new UserDAO();
                                                
                                                ArrayList<UserDetails> users = userDb.getAllUsers();
                                                
                                                for (int i = 0; i < users.size(); i++) {
                                                    UserDetails user = users.get(i);
                                                    int countRequest = userDb.getNumberOfRequests(user.getUserId());
                                                    
                                            %>
                                            <tr <% if (user.getStatus().equalsIgnoreCase("Inactive")) { %>
                                                class="deleted-row"
                                                <%}%>
                                                >
                                                <th scope="row"><%=i + 1%></th>
                                                <td><%=user.getUserId()%></td>
                                                <td><%=user.getFullname()%></td>
                                                <td><%=user.getUsername()%></td>
                                                <td><%=Constants.roleNames.get(user.getRoleId()) %></td>
                                                <td style="text-align: center"><%=countRequest%></td>
                                                <td>
                                                    <% if (user.getStatus().equalsIgnoreCase("Active")) { %>
                                                    ACTIVE
                                                    <% } else { %>
                                                    INACTIVE
                                                    <% }%>
                                                </td>
                                                <td><a href="UpdateUserStatusServlet?userId=<%=user.getUserId()%>">Enable/Disable</a></td>
                                            </tr>
                                            <% 
                                                }
                                            %>
                                            </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Table End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>
        <%
            } else
                request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
        %>
        <script>
function searchUsers() {
    
    var searchValue = document.getElementById("choices-text-preset-values").value.toLowerCase();
        
    var tableRows = document.querySelectorAll(".table tbody tr");

    for (var i = 0; i < tableRows.length; i++) {
        var fullName = tableRows[i].querySelector("td:nth-child(3)").textContent.toLowerCase();

        if (fullName.includes(searchValue)) {
            tableRows[i].style.display = "";
        } else {
            tableRows[i].style.display = "none";
        }
    }
}
document.querySelector(".btn-search").addEventListener("click", searchUsers);
document.getElementById("choices-text-preset-values").addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        searchUsers();
    }
});
        </script>
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

