<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
        <script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <style type="text/css">
            .form-gap {
                padding-top: 70px;
            }
        </style>
    </head>

    <body>
        <div class="form-gap"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3>
                                    <i class="fa fa-lock fa-4x"></i>
                                </h3>
                                <h2 class="text-center">Enter OTP</h2>
                                <%
                                    if (request.getAttribute("message") != null) {
                                        out.print("<p class='text-danger ml-1'>" + request.getAttribute("message") + "</p>");
                                    }

                                %>

                                <div class="panel-body">

                                    <form id="register-form" action="ValidateOtpServlet" role="form" autocomplete="off"
                                          class="form" method="post">

                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-envelope color-blue"></i></span> <input
                                                    id="opt" name="otp" placeholder="Enter OTP"
                                                    class="form-control" type="text" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input name="recover-submit"
                                                   class="btn btn-lg btn-primary btn-block"
                                                   value="Reset Password" type="submit">
                                        </div>

                                        <input type="hidden" class="hide" name="token" id="token"
                                               value="">
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>