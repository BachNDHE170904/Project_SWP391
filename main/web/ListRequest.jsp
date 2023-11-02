<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="model.Mentor"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="ViewProfileStyleIndex.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <style>
            .checked {
                color: orange;
            }

        </style>
    </head>

    <body>
        <%
            String msg = (String) session.getAttribute("successMsg");
            if (msg != null) {%>

        <script>
            swal("Congrats", "<%= msg%>", "success");
        </script>

        <% session.removeAttribute("successMsg");
            }%>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <div class="container light-style flex-grow-1 container-p-y">
                <h4 class="font-weight-bold py-3 mb-4">
                ${title}
            </h4>
            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <a class="list-group-item list-group-item-action active" data-toggle="list"
                               href="#account-general">List Request By Me</a>
                            <div class="sideBar col-md-9" style="margin-top: 20px; margin-left: 13px">
                                <h5>Filter by Status</h5>
                                <form id="status-filter-form">
                                    <input type="radio" name="status" value="Open" id="filter-open"> <label for="filter-open">Open</label><br>
                                    <input type="radio" name="status" value="Processing" id="filter-processing"> <label for="filter-processing">Processing</label><br>
                                    <input type="radio" name="status" value="Cancel" id="filter-cancel"> <label for="filter-cancel">Cancel</label><br>
                                    <input type="radio" name="status" value="Close" id="filter-close"> <label for="filter-close">Close</label><br>
                                    <input type="radio" checked name="status" value="All" id="filter-all"> <label for="filter-all">All</label><br>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <table class="table table-bordered" border="1" style="text-align: center">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Title</th>
                                        <th>Last Updated Date</th>
                                        <th>Deadline</th>
                                        <th>Status</th>
                                        <th>Assigned Mentor</th>
                                        <th colspan="4">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.list}" var="item" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${item.title}</td>
                                            <td>${item.createDate}</td>
                                            <td>${item.deadline}</td>
                                            <td>${item.status.name}</td>
                                            <c:if test="${item.mentorId==0}"><td>No one assigned</td></c:if>
                                            <c:if test="${item.mentorId!=0}"><td><a href="ViewMentorCV.jsp?mentorId=${item.mentorId}">${item.mentorId}</a></td></c:if>
                                            <c:if test="${ item.status.id==1 &&item.mentorId==0}"><td><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${item.id}">Update</a></td></c:if>
                                            <c:if test="${ item.status.id==1 &&item.mentorId==0}"><td><a href="#">Delete</a></td></c:if>
                                            <c:if test="${ item.status.id==1 &&item.mentorId==0}"><td><a href="#" data-toggle="modal" data-target="#mentorModal${item.id}">mentor suggestion</a></td></c:if>
                                            <td><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#feedbackModal${item.id}">Comment and Rate star</a></td>
                                        </tr>
                                    <div class="modal fade" id="exampleModal${item.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Update Request</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="updateRequest" method="post">
                                                        <input type="hidden" name="id" value="${item.id}">
                                                        <div class="form-group">
                                                            <label for="recipient-name" class="col-form-label">Title</label>
                                                            <input type="text" name="title" value="${item.title}" class="form-control" id="recipient-name" required="">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="recipient-name" class="col-form-label">DeadLine</label>
                                                            <input type="date" name="deadline" value="${item.deadline}" class="form-control" id="recipient-name" required="">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="content" class="col-form-label">Content</label>
                                                            <textarea name="content" class="form-control" id="content" required="">${item.content}</textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Programing Language</label>
                                                            <select class="form-control" name="pro">
                                                                <c:forEach items="${requestScope.pros}" var="i">
                                                                    <option value="${i.getLanguageId()}" ${i.getLanguageId() == item.pro.getLanguageId() ? 'selected' : ''}>${i.getLanguageName()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Skills (Select up to 3)</label>
                                                            <c:forEach items="${requestScope.skills}" var="i">
                                                                <c:set var="temp" value="0"/>
                                                                <c:forEach items="${item.skills}" var="a">
                                                                    <c:if test="${a.skillId == i.skillId}">
                                                                        <c:set var="temp" value="1"/>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <br>
                                                                <label>
                                                                    <input type="checkbox" class="form-check-input" name="selectedSkills" data-item-id="${item.id}" value="${i.skillId}" ${temp == 1 ? 'checked' : ''} onchange="limitSkills(this)">
                                                                    ${i.skillName}
                                                                </label>
                                                            </c:forEach>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            <button type="submit" class="btn btn-primary">Update</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade" id="feedbackModal${item.id}" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="feedbackModalLabel">Comment and Rate star</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="CommentAndRateStar" method="post">
                                                        <input type="hidden" name="id" value="${item.id}">
                                                        <div class="form-group">
                                                            <label for="comment" class="col-form-label">Feedback</label>
                                                            <textarea name="comment" class="form-control" id="comment" required></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="rating" class="col-form-label">Rating</label>
                                                            <input type="number" name="rating" class="form-control" id="rating" min="1" max="5" required>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            <button type="submit" class="btn btn-primary">Submit</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:forEach items="${requestScope.list}" var="item" varStatus="loop">
            <div class="modal fade" id="mentorModal${item.id}" tabindex="-1" role="table" aria-labelledby="mentorModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-xl" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="mentorModalLabel">Mentors suggestion</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="InviteMentorServlet" method="GET">
                                <input type="hidden" name="requestId" value="${item.id}">
                                <div>
                                    <table class="table table-bordered" border="1" style="text-align: center">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Full Name</th>
                                                <th>User Name</th>
                                                <th>Total ratings</th>
                                                <th>Average Ratings</th>
                                                <th>Current requests</th>
                                                <th colspan="1">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <c:forEach items="${requestScope.suggestedMentorList[item.id]}" var="mentor">
                                            <input type="hidden" name="mentorId" value="${mentor.mentorId}">
                                            <td>${mentor.fullname}</td>
                                            <td>${mentor.username}</td>
                                            <td>${mentor.totalRating}</td>
                                            <td>${mentor.averageRating}</td>
                                            <td>${mentor.currentRequests}</td>
                                            <td><button type="submit" class="btn btn-primary" >Invite</button></td>
                                        </c:forEach>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <script>
            document.getElementById("status-filter-form").addEventListener("change", function (e) {
                const selectedStatus = document.querySelector('input[name="status"]:checked').value;
                if (selectedStatus === "All") {
                    showAllRows();
                } else {
                    filterTableByStatus(selectedStatus);
                }
            });

            function filterTableByStatus(status) {
                const rows = document.querySelectorAll("table tbody tr");
                rows.forEach(row => {
                    const statusCell = row.querySelector("td:nth-child(5)"); // Số 5 ứng với cột Status
                    if (statusCell.textContent === status) {
                        row.style.display = "table-row";
                    } else {
                        row.style.display = "none";
                    }
                });
            }

            function showAllRows() {
                const rows = document.querySelectorAll("table tbody tr");
                rows.forEach(row => {
                    row.style.display = "table-row";
                });
            }
        </script>
        <script>
            document.getElementById("date").min = new Date().toISOString().split("T")[0];
        </script>
        <script type="text/javascript">
            function limitSkills(checkbox) {
                var maxSkills = 3;
                var itemId = checkbox.getAttribute('data-item-id');
                var selectedSkills = document.querySelectorAll('input[name="selectedSkills"][data-item-id="' + itemId + '"]:checked');

                if (selectedSkills.length > maxSkills) {
                    checkbox.checked = false; // Uncheck the current checkbox
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>