<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create CV of mentor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <style>
        .form-label {
            margin-bottom: -0.5rem;
        }
        .form-group{
            margin-bottom: 0.5rem;
        }
    </style>
    <body>
        <jsp:include page="../NavBar.jsp"></jsp:include>
            <div class="container light-style flex-grow-1 container-p-y">
                <h4 class="font-weight-bold py-3 mb-4">
                    Create CV
                </h4>
                <div class="card overflow-hidden">
                    <div class="row no-gutters row-bordered row-border-light">
                        <form action="CreateCV" method="POST" enctype="multipart/form-data">
                            <hr class="border-light m-0">
                            <div class="card-body" style="margin: 0 30px;">
                                <div class="form-group">
                                    <label class="form-label">Username <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="username">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Full Name <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="fullname">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Date of birth <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="dob">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">E-mail <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="email">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Gender <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="gender">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Address <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="address">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Profession <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="profession">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Profession introduction <font color="red">*</font></label>
                                    <input class="form-control mb-1" name="profession_introduction">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Skills <font color="red">*</font></label>
                                    <div class="d-flex flex-wrap">
                                    <% for (int i = 0; i < 20; i++) {%>
                                    <div class="me-3">
                                        <input type="checkbox"  value="1" name="skills" >
                                        <label>skill <%= i%></label>
                                    </div>
                                    <% }%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Service description <font color="red">*</font></label>
                                <input class="form-control mb-1" name="service_description">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Achievement description <font color="red">*</font></label>
                                <input class="form-control mb-1" name="achievement_description">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Programing (Framework) <font color="red">*</font></label>
                                <div class="d-flex flex-wrap">
                                    <% for (int i = 0; i < 20; i++) {%>
                                    <div class="me-3">
                                        <input type="checkbox"  value="1" name="programings" >
                                        <label>programing <%= i%></label>
                                    </div>
                                    <% }%>
                                </div>
                            </div>
                            <input type="submit" value="OK" class="btn btn-primary">
                            <label class="" style="color: red">loooooo</label>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    </body>
</html>
