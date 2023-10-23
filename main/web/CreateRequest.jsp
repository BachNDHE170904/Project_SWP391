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
                               href="#account-general">Create Request</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <form action="createRequest" method="post">
                                <div class="tab-pane fade active show" id="account-general">
                                    <hr class="border-light m-0">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label class="form-label">Title</label>
                                            <input class="form-control mb-1" type="text" name="title" placeholder="Input title here...." required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Deadline</label>
                                            <input class="form-control mb-1" id="date" type="date" name="deadline" placeholder="Input title here...." required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Programing Language</label>
                                            <select class="form-control mb-1" name="language" required="">
                                                <c:forEach items="${requestScope.lists}" var="item">
                                                    <option value="${item.languageId}">${item.getLanguageName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Skills</label>
                                            <c:forEach items="${requestScope.skills}" var="skill">
                                                <br>
                                                <label>
                                                    <input type="checkbox" class="form-check-input" name="selectedSkills" value="${skill.skillId}" >
                                                    ${skill.skillName}
                                                </label>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Content</label>
                                            <textarea class="form-control mb-1" name="content" placeholder="Input content here...." required=""></textarea>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="form-control mb-1">Create</button>
                                            <button type="reset" class="form-control mb-1">Reset</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <hr class="border-light m-0">

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