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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
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
                                        <th colspan="2">Action</th>
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
                                            <td><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${item.id}">Update</a></td>
                                            <td><a href="#">Delete</a></td>
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
                                                            <fmt:formatDate value="${item.deadline}" pattern="yyyy-MM-dd" var="deadline" />
                                                            <label for="recipient-name" class="col-form-label">DeadLine</label>
                                                            <input type="date" name="deadline" value="${deadline}" class="form-control" id="recipient-name" required="">
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
                                                                    <input type="checkbox" class="form-check-input" name="selectedSkills" value="${i.skillId}" ${temp == 1 ? 'checked' : ''}>
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
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>


            </div>

        </div>
        <script>
            document.getElementById("date").min = new Date().toISOString().split("T")[0];
        </script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>