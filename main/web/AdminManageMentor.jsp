<%@page import="dal.MentorDAO"%>
<%@page import="model.Mentor"%>
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
                int pageNum = 1;
                if (request.getParameter("page") != null) {
                    pageNum = Integer.parseInt(request.getParameter("page"));
                }
                String searchMentorname;
                if (request.getParameter("searchMentorname") == null && session.getAttribute("searchMentorname") == null) {
                    searchMentorname = "";
                } else if (request.getParameter("searchMentorname") != null) {
                    searchMentorname = request.getParameter("searchMentorname");
                    session.setAttribute("searchMentorname", searchMentorname);
                } else {
                    searchMentorname = (String) session.getAttribute("searchMentorname");
                }
                String filterStatus;
                if (request.getParameter("filterStatus") == null && session.getAttribute("filterStatus") == null) {
                    filterStatus = "";
                } else if (request.getParameter("filterStatus") != null) {
                    filterStatus = request.getParameter("filterStatus");
                    session.setAttribute("filterStatus", filterStatus);
                } else {
                    filterStatus = (String) session.getAttribute("filterStatus");
                }
                MentorDAO md = new MentorDAO();
                int total = md.getTotalMentorWithSearch(searchMentorname, filterStatus);
                int totalPage = (int) Math.ceil((double) (total) / 10);
                ArrayList<Mentor> mt = md.getMentorWithPagination((pageNum - 1) * 10, 10, searchMentorname, filterStatus);
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
                                    <h6 class="mb-4">Manage mentors</h6>
                                    <div class="inner-form">
                                        <form action="AdminManageMentor.jsp" >
                                            <div class="input-field">
                                                <input type="text" name="page" value="1" hidden/>
                                                <div class="form-group">
                                                    <input class="form-control"name="searchMentorname" type="text" placeholder="Type to search..." value="<%=searchMentorname%>"/>
                                            </div>
                                        </div>
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                Filter by Status
                                            </button>
                                            <ul class="dropdown-menu">
                                                <i class="dropdown-item" data-filter="" id="filter-1">All</i>
                                                <i class="dropdown-item" data-filter="active" id="filter-2">Active</i>
                                                <i class="dropdown-item" data-filter="inactive" id="filter-3">Inactive</i>
                                                <input type="hidden" name="filterStatus" id="selected-filter" value="" required>
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
                                                <th scope="col">Profession</th>
                                                <th scope="col">Number of currently accepted requests</th>
                                                <th scope="col">Percentage completed(%)</th>
                                                <th scope="col">Rate star</th>
                                                <th scope="col">Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%

                                                for (int i = 0; i < mt.size(); i++) {
                                                    Mentor mentor = mt.get(i);
                                                    double totalRating = md.getAverageRatingOfMentorByMentorId(mentor.getMentorId());
                                                    int res = md.getNumberOfRequestsMentorHas(mentor.getMentorId());
                                                    float percent = md.getPercentageCompleted(mentor.getMentorId());
                                            %>
                                            <tr <% if (mentor.getStatus().equalsIgnoreCase("Inactive")) { %>
                                                class="deleted-row"
                                                <%}%>
                                                >
                                                <th scope="row"><%=i + 1%></th>
                                                <td><%=mentor.getUserid()%></td>
                                                <td><%=mentor.getFullname()%></td>
                                                <td><%=mentor.getUsername()%></td>
                                                <td><%=mentor.getProfession()%></td>
                                                <td style="text-align: center"><%=res%></td>
                                                <td style="text-align: center"><%= percent%></td>
                                                <td style="text-align: center"><%=totalRating%></td>
                                                <td><a href="UpdateMentorStatusServlet?userId=<%=mentor.getUserid()%>&&page=<%= pageNum%>"><% if (mentor.getStatus().equalsIgnoreCase("Active")) { %>
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
                        <li class="page-item"><a class="page-link" href="AdminManageMentor.jsp?searchMentorname=<%=searchMentorname%>&page=<%=i%>&filterStatus=<%=filterStatus%>"><%= i%></a></li>
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
                    let filterStatus = filter.getAttribute("data-filter");
                    selectedFilter.value = filterStatus;
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

