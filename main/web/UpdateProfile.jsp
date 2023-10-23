<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    Update Profile
                </h4>
                <div class="card overflow-hidden">
                    <div class="row no-gutters row-bordered row-border-light">
                        <div class="col-md-3 pt-0">
                            <div class="list-group list-group-flush account-settings-links">
                                <a class="list-group-item list-group-item-action active" data-toggle="list"
                                   href="#account-general">Update Profile</a>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content">
                                <div class="tab-pane fade active show" id="account-general">
                                    <form action="updateProfile" method="post" enctype="multipart/form-data">
                                        <div class="container text-center">
                                            <div class="row">
                                                <div class="col-md-3">
                                                <c:if test="${avatar == null}">
                                                    <img class="img-thumbnail" alt="" src="img/default_avatar.jpg" />
                                                </c:if>
                                                <c:if test="${avatar != null}">
                                                    <img class="img-thumbnail" alt="" src="img/${avatar}" />
                                                </c:if>
                                                <input type="file" name="file">
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="border-light m-0">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label class="form-label">Username</label>
                                            <input class="form-control mb-1" type="text" name="username" value="${sessionScope.user.username}" required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Full Name</label>
                                            <input class="form-control mb-1" type="text" name="fullname" value="${sessionScope.userDetail.fullname}" required="">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Phone</label>
                                            <input class="form-control mb-1" type="text" name="phone" required="" value="${sessionScope.userDetail.phone}">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Address</label>
                                            <input class="form-control mb-1" type="text" name="address" required="" value="${sessionScope.userDetail.address}">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Sex</label>
                                            <select name="sex" class="form-control mb-1" required="">
                                                <option value="1" ${sessionScope.userDetail.sex != null && sessionScope.userDetail.sex eq true ? 'selected' : ''}>Male</option>
                                                <option value="0" ${sessionScope.userDetail.sex != null && sessionScope.userDetail.sex eq false ? 'selected' : ''}>Female</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Date Of Birth</label>
                                            <fmt:formatDate value="${sessionScope.userDetail.dob}"  
                                                            type="date" 
                                                            pattern="yyyy-MM-dd"
                                                            var="theFormattedDate" />
                                            <input class="form-control mb-1" type="date" name="dob" required="" value="${theFormattedDate}">
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-primary" type="submit">Ok</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>


            </div>

        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>

</html>