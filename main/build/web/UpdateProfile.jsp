
<!--Website: wwww.codingdung.com-->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta id="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <%
            //check if the user is logged in or not

            UserDetails details = (UserDetails) session.getAttribute("userDetail");
            if (session.getAttribute("user") == null) {
                response.sendRedirect("LoginServlet");
                return;
            }
        %>

        <%@include file="WelcomePage.jsp" %>

        <form name="formUpdate" action="UpdateProfileServlet" method="POST">
            <div class="container light-style flex-grow-1 container-p-y">
                <h4 class="font-weight-bold py-3 mb-4">
                    Account settings
                </h4>
                <div class="card overflow-hidden">
                    <div class="row no-gutters row-bordered row-border-light">
                        <div class="col-md-3 pt-0">
                            <div class="list-group list-group-flush account-settings-links">
                                <a class="list-group-item list-group-item-action active" data-toggle="list"
                                   href="#account-general">General</a>
                                <a class="list-group-item list-group-item-action" data-toggle="list"
                                   href="#account-info">Info</a>
                                <a class="list-group-item list-group-item-action" data-toggle="list"
                                   href="#account-change-password">Change password</a>


                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content">
                                <div class="tab-pane fade active show" id="account-general">

                                    <div style="height: 350px" class="card-body media align-items-center">
                                        <img id="demoimgadd" height="350px" src="${sessionScope.userDetail.getAvt()}" alt
                                             class="d-block ui-w-80">
                                        <div class="media-body ml-4">
                                            <label class="btn btn-outline-primary" style="">
                                                Upload new photo
                                                <input id="imgadd" onchange="changeimgadd()" name="image" type="file" class="account-settings-fileinput">
                                                <input name="proimage" id="imageadd" value="" type="hidden" >
                                            </label> &nbsp;
                                            <button type="button" class="btn btn-default md-btn-flat">Reset</button>
                                            <div class="text-light small mt-1">Allowed JPG, GIF or PNG. Max size of 800K</div>
                                        </div>
                                    </div>
                                    <hr class="border-light m-0">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label class="form-label">Username</label>
                                            <input type="text" class="form-control mb-1" id="username" name="username" value="${user.getUsername()}">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Full Name</label>
                                            <input type="text" class="form-control" id="fullname" name="fullname" value="${sessionScope.userDetail.getFullname()}">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">E-mail</label>
                                            <input type="text" class="form-control mb-1" name="email" value="${sessionScope.userDetail.getEmail()}" readonly>
                                            <div class="alert alert-warning mt-3">
                                                Your email is not confirmed. Please check your inbox.<br>
                                                <a href="javascript:void(0)">Resend confirmation</a>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <div class="tab-pane fade" id="account-change-password">
                                    <div class="card-body pb-2">
                                        <div class="form-group">
                                            <label class="form-label">Current password</label>
                                            <input type="password" name="currentPassword" value="" class="form-control" id="currentPassword" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">New password</label>
                                            <input type="password" name="newPassword"  class="form-control" id="newPassword" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Repeat new password</label>
                                            <input type="password"  name="repeatPassword" class="form-control" id="repeatPassword" required>
                                        </div>
                                    </div>
                                </div>


                                <div class="tab-pane fade" id="account-info">
                                    <div class="card-body pb-2">
                                        <div class="form-group">
                                            <label class="form-label">Birthday</label>
                                            <input type="date" class="form-control" id="dob" name="dob" value="${sessionScope.userDetail.getDob()}">
                                        </div>
                                        <div class="form-group">

                                            <label class="form-label">Sex </label>
                                            <select class="custom-select" name="gender">
                                                <option style="display:none"></option>
                                                <option ${sessionScope.userDetail.sex?"selected":""} value="0">Male</option>
                                                <option ${sessionScope.userDetail.sex?"":"selected"} value="1">Female</option>
                                                <option >Other</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Address</label>
                                            <input type="text" class="form-control" id="address" name="address" value="${sessionScope.userDetail.getAddress()}">
                                        </div>
                                        <div class="form-group">
                                            <label class="form-label">Phone</label>
                                            <input type="number" class="form-control" id="phone" name="phone" value="${sessionScope.userDetail.getPhone()}">
                                        </div>
                                    </div>
                                </div>

                                <hr class="border-light m-0">

                            </div>
                        </div>
                    </div>
                    <div class="text-right mt-3" style="margin-bottom: 1%">
                        <button type="submit" class="btn btn-primary" onclick="save()">Save changes</button>&nbsp;
                        <button class="btn btn-default">Cancel</button>
                    </div>



                </div>

            </div>
        </form>
        <script>
            function changeimgadd() {
                var file = document.getElementById("imgadd").files[0];
                if (file.name.match(/.+\.(jpg|png|jpeg)/i)) {
                    if (file.size / (1024 * 1024) < 50) {
                        var fileReader = new FileReader();
                        fileReader.readAsDataURL(file);
                        fileReader.onload = function () {
                            document.getElementById("imageadd").value = (fileReader.result);
                            document.getElementById("demoimgadd").src = (fileReader.result);
                        }
                    } else {
                        uploadError();
                    }
                } else {
                    uploadError();
                }
            }
            function uploadError() {
                alert('Please upload photo file < 5MB')
                document.getElementById("imgadd").files[0].value = ''
                document.getElementById("imgadd").type = '';
                document.getElementById("imgadd").type = 'file';
            }
        </script>

        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
            function save() {
                const form = document.getElementsByName('formUpdate')[0];
                //            console.log(form);
                form.submit();
            }
        </script>

    </body>

</html>