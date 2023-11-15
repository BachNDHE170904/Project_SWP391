<%@page import="dal.MentorDAO"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <%
            String emsg = (String) session.getAttribute("errorMsg");
            if (emsg != null) {%>
        <script>
            swal("Oops", "<%= emsg%>", "error");
        </script>
        <% session.removeAttribute("errorMsg");
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
                               href="#account-general">Statistic of requests by Me</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <table class="table table-bordered" border="1" style="text-align: center">
                                <c:set var="totalHours" value="0"/>

                                <thead class="thead-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>Title</th>
                                        <th>Last Updated Date</th>
                                        <th>Deadline</th>
                                        <th>Status</th>
                                        <th>Assigned Mentor</th>
                                        <th colspan="100%">Total Hours</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.list}" var="item" varStatus="loop">
                                        <tr>
                                            <td> <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${item.id}">${loop.index + 1}</a>
                                            </td>
                                            <td>${item.title}</td>
                                            <td>${item.createDate}</td>
                                            <td>${item.deadline}</td>
                                            <td>${item.status.name}</td>
                                            <c:if test="${item.mentorId==0}"><td>No one assigned</td></c:if>
                                            <c:if test="${item.mentorId!=0}"><td><a href="ViewMentorCV.jsp?mentorId=${item.mentorId}">${item.mentorEmail}</a></td></c:if>
                                                <td>
                                                <c:set var="createDateMillis" value="${item.createDate.time}" />
                                                <c:set var="deadlineMillis" value="${item.deadline.time}" />
                                                <c:set var="millisecondsPerDay" value="86400000" /> <!-- 24 hours * 60 minutes * 60 seconds * 1000 milliseconds -->
                                                <c:set var="daysBetween" value="${(deadlineMillis - createDateMillis) / millisecondsPerDay}" />
                                                <c:set var="totalHours" value="${totalHours + daysBetween}"/>
                                                <c:out value="${daysBetween}" /> day(s)

                                            </td>
                                        </tr>
                                    <div class="modal fade" id="exampleModal${item.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Request Details</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="updateRequest" method="post">
                                                        <fieldset <c:if test="${ item.status.id!=1 ||item.mentorId!=0}">disabled="disabled"</c:if>>
                                                            <input type="hidden" name="id" value="${item.id}">
                                                            <div class="form-group">
                                                                <label for="recipient-name" class="col-form-label">Title</label>
                                                                <input type="text" name="title" value="${item.title}" class="form-control" id="recipient-name" required="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="recipient-name" class="col-form-label">Price</label>
                                                                <input type="number" name="price" value="${item.menteePrice}" class="form-control"min="0" id="recipient-name" required="">
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
                                                        </fieldset>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </tbody>
                            </table>
                            <h5>Total requests: ${allRequests} request(s)</h5>
                            <h5>Total hours of all Request: <c:out value="${totalHours}" /> day(s)</h5>
                            <div class="col-12">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${page eq 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="statisticRequest?page=${page-1}" tabindex="-1"><</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page != 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="statisticRequest?page=${page-1}" tabindex="-1"><</a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="${1}" end="${total}" step="${1}" var="i">
                                        <c:if test="${page eq i}">
                                            <li class="page-item active">
                                                <a class="page-link" href="statisticRequest?page=${i}">${i}</a> 
                                                <span class="sr-only">(current)</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${page != i}">
                                            <li class="page-item">
                                                <a class="page-link" href="statisticRequest?page=${i}">${i}</a> 
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${page >= total}">
                                        <li>
                                            <a class="page-link disabled" href="statisticRequest?page=${page+1}">></a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page != total and page < total}">
                                        <li>
                                            <a class="page-link" href="statisticRequest?page=${page+1}">></a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.getElementById("date").min = new Date().toISOString().split("T")[0];
        </script>
        <script type="text/javascript">
            function limitSkills(checkbox) {
                var maxSkills = 3;
                var minSkills = 1;
                var itemId = checkbox.getAttribute('data-item-id');
                var selectedSkills = document.querySelectorAll('input[name="selectedSkills"][data-item-id="' + itemId + '"]:checked');

                if (selectedSkills.length > maxSkills) {
                    checkbox.checked = false; // Uncheck the current checkbox
                } else if (selectedSkills.length < minSkills) {
                    alert("Please select at least one skill");
                    checkbox.checked = true;
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>