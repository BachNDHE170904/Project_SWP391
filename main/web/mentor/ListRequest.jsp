
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>List of following requests</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link rel="stylesheet" href="ViewProfileStyleIndex.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <style>
            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .popup {
                width: 100%;
                height: 100%;
                position: absolute;
                top: 0;
                display: none;
                z-index: 1000;
            }

            .overlay {
                width: 100%;
                height: 100%;
                position: absolute;
                z-index: 12;
                background-color: rgba(22, 22, 22, 0.5);
            }

            .reveal-modal {
                background: #e1e1e1;
                margin: 0 auto;
                width: max-content;
                position: relative;
                z-index: 1000;
                top: 25%;
                padding: 14px;
                -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
                -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
            }
        </style>
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null || sessionScope.userDetail.getRoleId() != 4}">
                <jsp:forward page="../WelcomePage.jsp"></jsp:forward>
            </c:when>
            <c:otherwise>
                <jsp:include page="../NavBar.jsp"></jsp:include>
                    <div class="container light-style flex-grow-1 container-p-y">
                        <h4 class="font-weight-bold py-3 mb-4">List request</h4>
                        <button id="view-statistic">View statistic</button>
                        <div class="card overflow-hidden">
                            <div class="row no-gutters row-bordered row-border-light">
                                <div class="tab-content">
                                    <table class="table table-bordered" border="1" style="text-align: center">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>STT</th>
                                                <th>Title</th>
                                                <th>Deadline</th>
                                                <th>Content</th>
                                                <th>Skill</th>
                                                <th>Language</th>
                                                <th>Mentee</th>
                                                <th colspan="100%">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.list}" var="item" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${item.title}</td>
                                                <td>${item.deadline}</td>
                                                <td>${item.content}</td>
                                                <td>
                                                    <c:forEach items="${item.skills}" var="skill" varStatus="loop">
                                                        ${skill.skillName}, 
                                                    </c:forEach>
                                                </td>
                                                <td>${item.pro.languageName}</td>
                                                <td>${item.userName}</td>
                                                <td><a href="MentorRequestServlet?id=${item.id}"class="btn btn-primary" onclick="return confirm('Are you sure?')">Cancel</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="pagination">
                                    <a href="MentorRequestServlet?page=1">&laquo;</a>
                                    <c:forEach var="i" begin="1" end="${pageNum}">
                                        <c:choose>
                                            <c:when test="${i==currPage}">
                                                <a class="active" href="MentorRequestServlet?page=${i}">${i}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="MentorRequestServlet?page=${i}">${i}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <a href="MentorRequestServlet?page=${pageNum}">&raquo;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="popup">
                    <div class="overlay"></div>
                    <div id="exampleModal" class="reveal-modal">
                        <h2>Mentor Statistic</h2>
                        <div class="form-group">
                            <span>Accepted request: </span>
                            <span>${accepted}</span>
                        </div>
                        <div class="form-group">
                            <span>Invited request: </span>
                            <span>${invited}</span>
                        </div>
                        <div class="form-group">
                            <span>Canceled request: </span>
                            <span>${canceled}</span>
                        </div>
                        <div class="form-group">
                            <span>Completed request: </span>
                            <span>${completed}</span>
                        </div>
                        <div class="form-group">
                            <span>Rating: </span>
                            <span>${rating}</span>
                        </div>
                        <div class="modal-footer">
                            <button id="close" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
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
        <script>
                                                    $('button#view-statistic').click(function () {
                                                        $('div.popup').show();
                                                    });
                                                    $('div.overlay, button#close').click(function () {
                                                        $('div.popup').hide();
                                                    });
        </script>
    </body>

</html>