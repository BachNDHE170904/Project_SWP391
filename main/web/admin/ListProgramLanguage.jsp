
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            } %>
        <c:choose>
            <c:when test="${sessionScope.user == null || sessionScope.userDetail.getRoleId() != 1}">
                <jsp:forward page="../WelcomePage.jsp"></jsp:forward>
            </c:when>
            <c:otherwise>
                <div class="container-fluid position-relative bg-white d-flex p-0">
                    <!-- Sidebar Start -->
                    <jsp:include page="../DashBoardSideBar.jsp"></jsp:include>
                        <!-- Sidebar End -->
                        <!-- Content Start -->
                        <div class="content">
                            <!-- Navbar Start -->
                        <jsp:include page="../NavBar.jsp"></jsp:include>
                            <!-- Navbar End -->
                            <!-- Table Start -->
                            <div class="container-fluid pt-4 px-4">
                                <div class="row g-4">
                                    <div class="col-12">
                                        <div class="bg-light rounded h-100 p-4">
                                            <h6 class="mb-4">Manage program language</h6>
                                            <div class="table-responsive">
                                                <div class="search-container">
                                                    <div style="display: contents;">
                                                        <div class="form-group">
                                                            <input class="form-control"name="searchValue" id="search" type="text" placeholder="Type to search..." value="${key}"/>
                                                    </div>
                                                </div>
                                                <div style="margin-right: 10px;">
                                                    <label class="col-form-label">Status: </label>
                                                    <select id="status" onchange="status()">
                                                        <option value="-1">All</option>
                                                        <option value="1" <c:if test="${status == 1}">selected</c:if>>Active</option>
                                                        <option value="0" <c:if test="${status == 0}">selected</c:if>>Inactive</option>
                                                        </select>
                                                        <button class="btn btn-primary" onclick="search()">Filter</button>
                                                    </div>
                                                </div>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">ID</th>
                                                            <th scope="col">Language Name</th>
                                                            <th scope="col">Status</th>
                                                            <th scope="col">Action</th>
                                                            <th scope="col"><a href="ProgramLanguageServlet?action=add">+</a></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${programingLanguages}" var="programingLanguage" varStatus="i">
                                                        <tr<c:if test="${'Inactive'.equalsIgnoreCase(programingLanguage.getLanguageStatus())}"> class="deleted-row" </c:if>>
                                                            <th scope="row">${i.index + 1}</th>
                                                            <td>${programingLanguage.getLanguageId()}</td>
                                                            <td>${programingLanguage.getLanguageName()}</td>
                                                            <td><a href="ProgramLanguageServlet?action=update_status&id=${programingLanguage.getLanguageId()}">${programingLanguage.getLanguageStatus()}</a></td>
                                                            <td><a href="ProgramLanguageServlet?action=update&id=${programingLanguage.getLanguageId()}">Update</a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <nav aria-label="...">
                                                <ul class="pagination">
                                                    <li class="page-item">
                                                        <a class="page-link" href="ProgramLanguageServlet?page=1&key=${key}&status= ${status}">&laquo;</a>
                                                    </li>
                                                    <c:forEach var="i" begin="1" end="${pageNum}">
                                                        <c:choose>
                                                            <c:when test="${i==currPage}">
                                                                <li class="page-item">
                                                                    <a class="page-link active" href="ProgramLanguageServlet?page=${i}&key=${key}&status= ${status}">${i}</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="page-item">
                                                                    <a class="page-link" href="ProgramLanguageServlet?page=${i}&key=${key}&status= ${status}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <li class="page-item">
                                                        <a class="page-link" href="ProgramLanguageServlet?page=${pageNum}&key=${key}&status= ${status}">&raquo;</a>
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
            </c:otherwise>
        </c:choose>
        <script>
            function search() {
                let key = document.getElementById('search').value;
                location.replace("${pageContext.servletContext.contextPath}/ProgramLanguageServlet?key=" + key + "&status= ${status}");
            }
            function status() {
                let status = document.getElementById('status').value;
                location.replace("${pageContext.servletContext.contextPath}/ProgramLanguageServlet?key=${key}&status=" + status);
            }
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