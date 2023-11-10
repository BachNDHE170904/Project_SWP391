<%@page import="model.User"%>
<%@page import="dal.UserDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vnpay.common.Config"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="../assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="../assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="../assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
            String dateString = request.getParameter("vnp_PayDate");
            // Define the date format of the retrieved string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            // Parse the string into a java.util.Date object
            java.util.Date utilDate = dateFormat.parse(dateString);

            // Convert java.util.Date to java.sql.Date
            Date sqlDate = new Date(utilDate.getTime());

            String amountString = request.getParameter("vnp_Amount");
            long amount = Integer.parseInt(amountString) / 100;
        %>
        <!--Begin display -->
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label >Mã giao dịch thanh toán:</label>
                    <label><%=request.getParameter("vnp_TxnRef")%></label>
                </div>    
                <div class="form-group">
                    <label >Số tiền:</label>
                    <label><%=amount%>VND</label>
                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch:</label>
                    <label><%=request.getParameter("vnp_OrderInfo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>
                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán:</label>
                    <label><%=dateString%></label>
                </div> 
                <div class="form-group">
                    <label >Tình trạng giao dịch:</label>
                    <label>
                        <%
                            if (signValue.equals(vnp_SecureHash)) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    UserDAO userDAO = new UserDAO();
                                    User acc = null;
                                    //check if the user is logged in or not
                                    if (session.getAttribute("user") != null) {
                                        acc = (User) session.getAttribute("user");
                                    }
                                    if (!userDAO.insertAcountBalance(acc.getUserId(), amount)) {
                                        userDAO.updateAcountBalance(acc.getUserId(), amount);
                                    }
                                    out.print("Thành công");
                                } else {
                                    out.print("Không thành công");
                                }
                            } else {
                                out.print("invalid signature");
                            }
                        %></label>
                </div>
                <a href="../WelcomePage.jsp" class="btn btn-primary">Go back to home page</a>
            </div>
            <p>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>  
    </body>
</html>
