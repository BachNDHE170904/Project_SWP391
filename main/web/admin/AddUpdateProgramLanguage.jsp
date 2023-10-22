
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
        </head>

        <body>
            <c:choose>
                <c:when test="${sessionScope.user == null && sessionScope.userDetail.getRoleId() != 1}">
                    <jsp:forward page="../WelcomePage.jsp"></jsp:forward>
                </c:when>
                <c:otherwise>
                    <div class="container-fluid position-relative bg-white d-flex p-0">
                        <!-- Sidebar Start -->
                        <jsp:include page="../DashBoardSideBar.jsp"></jsp:include>
                            <!-- Sidebar End -->
                            <div class="content">
                                <!-- Content Start -->
                            <jsp:include page="../NavBar.jsp"></jsp:include>
                                <!-- Form Start -->
                                <div class="col-sm-12 col-xl-12">
                                    <div class="bg-light rounded h-100 p-4">
                                        <form action="ProgramLanguageServlet" method="POST">
                                            <input type="hidden" value="${language.getLanguageId()}" name="id">
                                            <input type="hidden" value="${action}" name="action">
                                            <c:choose>
                                                <c:when test="${'add'.equals(action)}">
                                                    <h6 class="mb-4">Create new Program Language</h6>
                                                </c:when>
                                                <c:otherwise>
                                                    <h6 class="mb-4">Update Program Language</h6>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="floatingInput" name="name" value="${language.getLanguageName()}" required>
                                                <label for="floatingInput">Program Language Name</label>
                                            </div>
                                            <fieldset class="row mb-3">
                                                <legend class="col-form-label col-sm-2 pt-0">Status</legend>
                                                <div class="col-sm-10">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="status" id="gridRadios1" value="Active" <c:if test="${'Active'.equalsIgnoreCase(language.getLanguageStatus())||languagelanguage.getLanguageStatus()==null}">checked</c:if>>
                                                        <label class="form-check-label" for="gridRadios1">Active</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="status"id="gridRadios2" value="Inactive" <c:if test="${'Inactive'.equalsIgnoreCase(language.getLanguageStatus())}">checked</c:if>>
                                                        <label class="form-check-label" for="gridRadios2">Inactive</label>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <button type="submit" class="btn btn-primary">OK</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- Form End -->
                        </div>
                        <!-- Content End -->
                        <!-- Back to Top -->
                        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
                    </div>
            </c:otherwise>
        </c:choose>
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