
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
        <link rel="stylesheet" href="ViewProfileStyleIndex.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>

    <body>
        <%
            String msg = (String) session.getAttribute("successMsg");
            if (msg != null) {%>
        <script>
            swal("Congrats", "<%= msg%>", "success");
        </script>
        <% session.removeAttribute("successMsg");
            }
            String emsg = (String) session.getAttribute("errorMsg");
            if (emsg != null) {%>
        <script>
            swal("Oops", "<%= emsg%>", "error");
        </script>
        <% session.removeAttribute("errorMsg");
            }
            //check if the user is logged in or not
            User acc = (User) session.getAttribute("user");
            UserDetails details = (UserDetails) session.getAttribute("userDetail");;
            if (acc != null && details.getRoleId() == 1) {
                int pageNum = 1;
                if (request.getParameter("page") != null) {
                    pageNum = Integer.parseInt(request.getParameter("page"));
                }
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
                SkillDAO skillDb = new SkillDAO();
                int total = skillDb.getTotalSkillsWithSearch(searchValue, filterValue);
                int totalPage = (int) Math.ceil((double) (total) / 10);
                ArrayList<Skill> skills = skillDb.getSkillsWithPagination((pageNum - 1) * 10, 10, searchValue, filterValue);
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
                                        <form action="AdminManageSkills.jsp" >
                                            <div class="input-field">
                                                <input type="text" name="page" value="1" hidden/>
                                                <div class="form-group">
                                                    <input class="form-control"name="searchValue" type="text" placeholder="Type to search..." value="<%=searchValue%>"/>
                                            </div>
                                        </div>
                                        <label class="col-form-label">Status: </label>
                                        <select name="filterValue">
                                            <option value="" >All</option>
                                            <option value="Active" <%if (filterValue.equalsIgnoreCase("Active")) {%>selected<%}%>>Active</option>
                                            <option value="Inactive"<%if (filterValue.equalsIgnoreCase("Inactive")) {%>selected<%}%>>Inactive</option>
                                        </select>
                                        <button class="btn btn-primary" type="submit">Filter</button>
                                    </form>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">ID</th>
                                                <th scope="col">Skill Name</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Action</th>
                                                <th scope="col"><a href="CreateNewSkill.jsp?&&page=<%= pageNum%>">+</a></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < skills.size(); i++) {
                                                    Skill skill = skills.get(i);
                                            %>
                                            <tr 
                                                <% if (skill.getSkillStatus().equals("inactive")) {%>class="deleted-row"<%}%>>
                                                <td><%=i + 1%></td>
                                                <td><%=skill.getSkillId()%></td>
                                                <td><%=skill.getSkillName()%></td>
                                                <td><a href="UpdateSkillStatusServlet?skillId=<%=skill.getSkillId()%>&&page=<%= pageNum%>"><%=skill.getSkillStatus()%></a></td>
                                                <td><a href="UpdateSkill.jsp?skillId=<%=skill.getSkillId()%>&&page=<%= pageNum%>">Update</a></td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                    <nav aria-label="...">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="AdminManageSkills.jsp?searchValue=<%=searchValue%>&page=1&filterValue=<%=filterValue%>">&laquo;</a>
                                            </li>
                                            <%for (int i = 1; i <= totalPage; i++) {%>
                                            <li class="page-item">
                                                <a class="page-link <%if (i == pageNum) {%> active <%}%>" href="AdminManageSkills.jsp?searchValue=<%=searchValue%>&page=<%=i%>&filterValue=<%=filterValue%>"><%= i%></a>
                                            </li>
                                            <%}%>
                                            <li class="page-item">
                                                <a class="page-link" href="AdminManageSkills.jsp?searchValue=<%=searchValue%>&page=<%=totalPage%>&filterValue=<%=filterValue%>">&raquo;</a>
                                            </li>
                                        </ul>
                                    </nav>
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