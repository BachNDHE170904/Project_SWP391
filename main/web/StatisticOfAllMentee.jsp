
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
                String searchname;
                if (request.getParameter("searchname") == null && session.getAttribute("searchname") == null) {
                    searchname = "";
                } else if (request.getParameter("searchname") != null) {
                    searchname = request.getParameter("searchname");
                    session.setAttribute("searchname", searchname);
                } else {
                    searchname = (String) session.getAttribute("searchname");
                }
                UserDAO ud = new UserDAO();
                int total = ud.getTotalUsersWithSearch(searchname);
                int totalPage = (int) Math.ceil((double) (total) / 10);
                ArrayList<UserDetails> usd = ud.getUsersWithPagination((pageNum - 1) * 10, 10, searchname);
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
                                    <h6 class="mb-4">Statistic Of All Mentee</h6>
                                    <div class="inner-form">
                                        <form action="StatisticOfAllMentee.jsp" >
                                            <div class="input-field">
                                                <input type="text" name="page" value="1" hidden/>
                                                <div class="form-group">
                                                    <input class="form-control"name="searchname" type="text" placeholder="Type to search..." value="<%=searchname%>" id="myInput"/>
                                                <button type="submit" id="myBtn" style="background-color: #0dcaf0; color: #ffffff">Search</button>
                                            </div>
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
                                                <th scope="col">Total of all requests</th>
                                                <th scope="col">Total of skills of all requests</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%

                                                for (int i = 0; i < usd.size(); i++) {
                                                    UserDetails user = usd.get(i);
                                                    int countRequest = ud.getAllNumberOfRequests(user.getUserId());
                                                    int countSkill = ud.getAllNumberOfSkillOfRequests(user.getUserId());
                                            %>
                                            <tr <% if (user.getStatus().equalsIgnoreCase("Inactive")) { %>
                                                class="deleted-row"
                                                <%}%>
                                                >
                                                <th scope="row"><%=i + 1%></th>
                                                <td><%=user.getUserId()%></td>
                                                <td><%=user.getFullname()%></td>
                                                <td><%=user.getUsername()%></td>
                                                <td style="text-align: center"><%=countRequest%></td>
                                                <td style="text-align: center"><%=countSkill%></td>
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
                    <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="StatisticOfAllMentee.jsp?searchname=<%=searchname%>&page=1">&laquo;</a>
                                            </li>
                                            <%for (int i = 1; i <= totalPage; i++) {%>
                                            <li class="page-item">
                                                <a class="page-link <%if (i == pageNum) {%> active <%}%>" href="StatisticOfAllMentee.jsp?searchname=<%=searchname%>&page=<%=i%>"><%= i%></a>
                                            </li>
                                            <%}%>
                                            <li class="page-item">
                                                <a class="page-link" href="StatisticOfAllMentee.jsp?searchname=<%=searchname%>&page=<%=totalPage%>">&raquo;</a>
                                            </li>
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
                    let filterRole = filter.getAttribute("data-filter");
                    selectedFilter.value = filterRole;
                });
            });
            var input = document.getElementById("myInput");

// Execute a function when the user presses a key on the keyboard
            input.addEventListener("keypress", function (event) {
                // If the user presses the "Enter" key on the keyboard
                if (event.key === "Enter") {
                    // Cancel the default action, if needed
                    event.preventDefault();
                    // Trigger the button element with a click
                    document.getElementById("myBtn").click();
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

