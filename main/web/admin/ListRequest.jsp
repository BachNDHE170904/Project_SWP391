
<%@page import="model.Request"%>
<%@page import="java.util.List"%>
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
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null && sessionScope.userDetail.getRoleId() != 1}">
                <jsp:forward page="../WelcomePage.jsp"></jsp:forward>
            </c:when>
            <c:otherwise>
                <%
                    String msg = (String) session.getAttribute("successMsg");
                    if (msg != null) {%>
                <script>
                    swal("Congrats", "<%= msg%>", "success");
                </script>
                <% session.removeAttribute("successMsg");
                    }%>
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
                                            <h6 class="mb-4">Manage Requests</h6>
                                            <div class="table-responsive">
                                                <div class="search-container">
                                                    <div style="display: contents;">
                                                        <div class="form-group">
                                                            <input class="form-control"name="searchValue" id="search" type="text" placeholder="Type to search..." value="${key}"/>
                                                    </div>
                                                </div>
                                                <label class="col-form-label">Status: </label>
                                                <select id="status" onchange="status()">
                                                    <option value="-1">All</option>
                                                    <option value="1" <c:if test="${status == 1}">selected</c:if>>Open</option>
                                                    <option value="2" <c:if test="${status == 2}">selected</c:if>>Processing</option>
                                                    <option value="3" <c:if test="${status == 3}">selected</c:if>>Cancel</option>
                                                    <option value="4" <c:if test="${status == 4}">selected</c:if>>Closed</option>
                                                    </select>
                                                    <button class="btn btn-primary" onclick="search()">Filter</button>
                                                </div>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">ID</th>
                                                            <th scope="col">Account Name</th>
                                                            <th scope="col">Title</th>
                                                            <th scope="col">Status</th>
                                                            <th scope="col">Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.listRequests}" var="item" varStatus="loop">
                                                        <tr>
                                                    <form action="updateAdminRequest" method="post">
                                                        <input type="hidden" name="id" value="${item.id}">
                                                        <td>${loop.index + 1}</td>
                                                        <td><a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${item.id}">${item.id}</a></td>
                                                        <td>${item.userName}</td>
                                                        <td>${item.title}</td>
                                                        <td>
                                                            <select  name="statusId" required="">
                                                                <c:forEach items="${requestScope.statuses}" var="statusItem">
                                                                    <option value="${statusItem.id}"
                                                                            <c:if test="${item.status.id eq statusItem.id}">selected=""</c:if>
                                                                            >${statusItem.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td><button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Save</button></td>
                                                    </form>
                                                    </tr>
                                                    <div class="modal fade" id="exampleModal${item.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Request Details</h5>
                                                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">Title</label>
                                                                        <input type="text" readonly name="title" value="${item.title}" class="form-control" id="recipient-name" required="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">Price</label>
                                                                        <input type="number" readonly name="price" value="${item.menteePrice}" class="form-control"min="0" id="recipient-name" required="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">DeadLine</label>
                                                                        <input type="date" readonly name="deadline" value="${item.deadline}" class="form-control" id="recipient-name" required="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">Mentor id</label>
                                                                        <input type="text" readonly name="title" <c:if test="${ item.mentorId!=0 }">value="${item.mentorId}"</c:if><c:if test="${ item.mentorId==0 }">value="No mentor assigned" </c:if> class="form-control" id="recipient-name" required="">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="content" class="col-form-label">Content</label>
                                                                                <textarea name="content" readonly class="form-control" id="content" required="">${item.content}</textarea>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Programing Language</label>
                                                                        <select class="form-control" disabled name="pro">
                                                                            <c:forEach items="${requestScope.pros}" var="i">
                                                                                <option value="${i.getLanguageId()}" ${i.getLanguageId() == item.pro.getLanguageId() ? 'selected' : ''}>${i.getLanguageName()}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Skills</label>
                                                                        <c:forEach items="${requestScope.skills}" var="i">
                                                                            <c:set var="temp" value="0"/>
                                                                            <c:forEach items="${item.skills}" var="a">
                                                                                <c:if test="${a.skillId == i.skillId}">
                                                                                    <c:set var="temp" value="1"/>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <br>
                                                                            <label>
                                                                                <input type="checkbox" onclick="return false;" class="form-check-input" name="selectedSkills" value="${i.skillId}" ${temp == 1 ? 'checked' : ''}>
                                                                                ${i.skillName}
                                                                            </label>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                                </tbody>
                                            </table>

                                            <div class="col-12">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page eq 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="ListRequestController?page=${page-1}&key=${key}&status= ${status}" tabindex="-1"><</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page != 1}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="ListRequestController?page=${page-1}&key=${key}&status= ${status}" tabindex="-1"><</a>
                                                        </li>
                                                    </c:if>

                                                    <c:forEach begin="${1}" end="${total}" step="${1}" var="i">
                                                        <c:if test="${page eq i}">
                                                            <li class="page-item active">
                                                                <a class="page-link" href="ListRequestController?page=${i}&key=${key}&status= ${status}">${i}</a> 
                                                                <span class="sr-only">(current)</span>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${page != i}">
                                                            <li class="page-item">
                                                                <a class="page-link" href="ListRequestController?page=${i}&key=${key}&status= ${status}">${i}</a> 
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${page == total}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="ListRequestController?page=${page+1}&key=${key}&status= ${status}">></a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page != total and page < total}">
                                                        <li>
                                                            <a class="page-link" href="ListRequestController?page=${page+1}&key=${key}&status= ${status}">></a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>

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
                location.replace("${pageContext.servletContext.contextPath}/ListRequestController?key=" + key + "&status= ${status}");
            }
            function status() {
                let status = document.getElementById('status').value;
                location.replace("${pageContext.servletContext.contextPath}/ListRequestController?key=${key}&status=" + status);
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