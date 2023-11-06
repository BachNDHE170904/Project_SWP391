
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
            if (acc != null && details.getRoleId() == 1) {
            int pageNum = Integer.parseInt(request.getParameter("page"));
            String searchValue;
                if (request.getParameter("searchValue") == null && session.getAttribute("searchValue") == null) {
                    searchValue = "";
                } else if (request.getParameter("searchValue") != null) {
                    searchValue = request.getParameter("searchValue");
                    session.setAttribute("searchValue", searchValue);
                } else {
                    searchValue = (String) session.getAttribute("searchValue");
                }
            String filterValue;
                if (request.getParameter("filterValue") == null && session.getAttribute("filterValue") == null) {
                    filterValue = "";
                } else if (request.getParameter("filterValue") != null) {
                    filterValue = request.getParameter("filterValue");
                    session.setAttribute("filterValue", filterValue);
                } else {
                    filterValue = (String) session.getAttribute("filterValue");
                }
            UserDAO ud = new UserDAO();
            int total = ud.getTotalUsersWithSearch(searchValue, filterValue);
            ArrayList<UserDetails> us = ud.getUsersWithPagination((pageNum - 1) * 10, 10, searchValue, filterValue);
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
                                        <form action="AdminManageUsers.jsp" >
                                            <div class="input-field">
                                                <input type="text" name="page" value="1" hidden/>
                                                <div class="form-group">
                                                    <input class="form-control"name="searchValue" type="text" placeholder="Type to search..." value="<%=searchValue%>"/>
                                            </div>
                                        </div>
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                Filter by Role
                                            </button>
                                            <ul class="dropdown-menu">
                                                <i class="dropdown-item" data-filter="" id="filter-1">All</i>
                                                <i class="dropdown-item" data-filter="mentee" id="filter-2">Mentee</i>
                                                <i class="dropdown-item" data-filter="user" id="filter-3">User</i>
                                                <input type="hidden" name="filterValue" id="selected-filter" value="" required>
                                            </ul>
                                            <button class="btn btn-primary" type="submit">Filter</button>
                                        </div>
                                    </form>
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
                                                
                                                <td><a href="UpdateUserStatusServlet?userId=<%=user.getUserId()%>&&page=<%= pageNum%>"><% if (user.getStatus().equalsIgnoreCase("Active")) { %>
                                                    ACTIVE
                                                    <% } else { %>
                                                    INACTIVE
                                                    <% }%></a></td>
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
                <nav aria-label="...">
                    <ul class="pagination pagination-sm">
                        <%for (int i = 1; i <= (int) Math.ceil((double) (total) / 10); i++) {%>
                        <li class="page-item"><a class="page-link" href="AdminManageUsers.jsp?searchValue=<%=searchValue%>&page=<%=i%>&filterValue=<%=filterValue%>"><%= i%></a></li>
                            <%}%>
                    </ul>
                </nav>
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
            let filters = document.querySelectorAll(".dropdown-item");
            let selectedFilter = document.getElementById("selected-filter");
            filters.forEach(filter => {
                filter.addEventListener("click", () => {
                    let filterValue = filter.getAttribute("data-filter");
                    selectedFilter.value = filterValue;
                });
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

