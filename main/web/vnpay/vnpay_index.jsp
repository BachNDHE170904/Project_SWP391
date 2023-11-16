<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cổng thanh toán VNPAY</title>
        <!-- Bootstrap core CSS -->
        <link href="../assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="../assets/jumbotron-narrow.css" rel="stylesheet">      
        <script src="../assets/jquery-1.11.3.min.js"></script>
    </head>

    <body>
        <%
            User acc = null;
            //check if the user is logged in or not
            if (session.getAttribute("user") != null) {
                acc = (User) session.getAttribute("user");
            }
            UserDetails details = (UserDetails) session.getAttribute("userDetail");
            if(acc==null||details.getRoleId()!=3)response.sendRedirect("../WelcomePage.jsp");
        %>
        <div class="container">
            <div class="header clearfix">

                <h3 class="text-muted">VNPAY </h3>
            </div>
            <div class="form-group">
                <button onclick="pay()">Giao dịch thanh toán</button><br>
            </div>
            <!--                <div class="form-group">
                                <button onclick="querydr()">API truy vấn kết quả thanh toán</button><br>
                            </div>
                            <div class="form-group">
                                <button onclick="refund()">API hoàn tiền giao dịch</button><br>
                            </div>-->
            <p>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div> 
        <script>
            function pay() {
                window.location.href = "../vnpay/vnpay_pay.jsp";
            }
//            function querydr() {
//              window.location.href = "../vnpay/vnpay_querydr.jsp";
//            }
//             function refund() {
//              window.location.href = "../vnpay/vnpay_refund.jsp";
//            }
        </script>
    </body>
</html>
