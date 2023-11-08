
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
                        <h4 class="font-weight-bold py-3 mb-4">List of following requests</h4>
                        <div class="card overflow-hidden">
                            <div class="row no-gutters row-bordered row-border-light">
                                <div class="tab-content">
                                    <table class="table table-bordered" border="1" style="text-align: center">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>STT</th>
                                                <th>Title</th>
                                                <th>Deadline</th>
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
                                                <td>${item.userName}</td>
                                                <td><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${item.id}">Details</a></td>
                                                <td><a href="MentorRequestServlet?id=${item.id}"class="btn btn-danger" onclick="return confirm('Are you sure?')">Cancel</a></td>
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
                                                        <fieldset <c:if test="${ item.status.id!=1 ||item.mentorId!=0}">disabled="disabled"</c:if>>
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
                                                                        <input type="checkbox" class="form-check-input" name="selectedSkills" data-item-id="${item.id}" value="${i.skillId}" ${temp == 1 ? 'checked' : ''} onchange="limitSkills(this)">
                                                                        ${i.skillName}
                                                                    </label>
                                                                </c:forEach>
                                                            </div>
                                                        </fieldset>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
            </c:otherwise>
        </c:choose>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>