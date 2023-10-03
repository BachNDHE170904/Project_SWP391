<%-- 
    Document   : change
    Created on : Sep 18, 2023, 1:04:12 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        h3 {
            color: red;
        }

        form {
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 20px;
            max-width: 400px;
            margin: 0 auto;
        }

        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
    </head>
    <body>
        <input type="hidden" id="status" value="<%=request.getAttribute("status")%>}"/>
        <h1>Change password</h1>
        <h3 style="color:red">${sessionScope.ms}</h3>
        <form action="change" method="GET">
            <div class="col-md-6 offset-md-3">
                <span class="anchor" id="formChangePassword"></span>
                <hr class="mb-5">

                <!-- form card change password -->
                <div class="card card-outline-secondary">
                    <div class="card-header">
                        <h3 class="mb-0">Change Password</h3>
                    </div>
                    <div class="card-body">
                        <form class="form" role="form" autocomplete="off">
                            <div class="form-group">
                                <label for="inputPasswordOld">Current Password</label>
                                <input type="password" class="form-control" id="inputPasswordOld" required="">
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordNew">New Password</label>
                                <input type="password" class="form-control" id="inputPasswordNew" required="">
                                <span class="form-text small text-muted">
                                        The password must be 8-20 characters, and must <em>not</em> contain spaces.
                                    </span>
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordNewVerify">Verify</label>
                                <input type="password" class="form-control" id="inputPasswordNewVerify" required="">
                                <span class="form-text small text-muted">
                                        To confirm, type the new password again.
                                    </span>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-lg float-right">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
        </form>
    </body>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script type="text/javascript">
        var status = document.getElementById(status).value;
        if(status==="success"){
            swal("Congrats","Change password succesfully", "success");
        }
    </script>
</html>
