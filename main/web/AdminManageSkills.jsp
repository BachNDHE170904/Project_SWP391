
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
            UserDetails details = (UserDetails) session.getAttribute("userDetail");;
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
                SkillDAO skillDb = new SkillDAO();
                int total = skillDb.getTotalSkillsWithSearch(searchValue);
                ArrayList<Skill> skills = skillDb.getSkillsWithPagination((pageNum - 1) * 10, 10, searchValue);
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
                                    <h6 class="mb-4">Manage skills</h6>
                                    <div class="inner-form">
                                        <div class="input-field">
                                            <form action="AdminManageSkills.jsp" >
                                                <input type="text" name="page" value="1" hidden/>
                                            <input class="form-control"name="searchValue" type="text" placeholder="Type to search..." value="<%=searchValue%>"/>
                                            <button class="btn-search" type="submit">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                                </svg>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Skill Name</th>
                                                <th scope="col">Enable/Disable</th>
                                                <th scope="col">Update</th>
                                                <th scope="col"><a href="CreateNewSkill.jsp?&&page=<%= pageNum%>">+</a></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < skills.size(); i++) {
                                                    Skill skill = skills.get(i);
                                            %>
                                            <tr 
                                                <% if (skill.getSkillStatus().equals("inactive")) { %>
                                                class="deleted-row"
                                                <%}%>
                                                >
                                                <td><%=skill.getSkillId()%></td>
                                                <td><%=skill.getSkillName()%></td>
                                                <td><a href="UpdateSkillStatusServlet?skillId=<%=skill.getSkillId()%>&&page=<%= pageNum%>"><%=skill.getSkillStatus()%></a></td>
                                                <td><a href="UpdateSkill.jsp?skillId=<%=skill.getSkillId()%>&&page=<%= pageNum%>">Update</a></td>
                                            </tr>
                                            <% } %>
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
                        <li class="page-item"><a class="page-link" href="AdminManageSkills.jsp?searchValue=<%=searchValue%>&page=<%=i%>"><%= i%></a></li>
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