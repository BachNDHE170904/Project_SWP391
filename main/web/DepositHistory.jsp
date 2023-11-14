<%-- 
    Document   : DepositHistory.jsp
    Created on : Nov 14, 2023, 5:44:59 PM
    Author     : admin
--%>

<%@page import="model.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Deposit History</title>
        <link rel="stylesheet" href="ViewProfileStyleIndex.css">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                               href="#account-general">Deposit History</a>

                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-condensed table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col"><font face="verdana">#</font></th>
                                        <th scope="col"><font face="verdana">Time</font></th>
                                        <th scope="col"><font face="verdana">Amount of money</font></th>
                                        <th scope="col"><font face="verdana">Content</font></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="t" items="${transactions}">

                                        <tr>
                                            <td>${t.transactionId}</td>
                                            <td>${t.createdDate}</td>
                                            <td>${t.amount}</td> 
                                            <td>${t.content}</td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="col-12">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${page eq 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="DepositHistoryServlet?page=${page-1}" tabindex="-1"><</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page != 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="DepositHistoryServlet?page=${page-1}" tabindex="-1"><</a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="${1}" end="${total}" step="${1}" var="i">
                                        <c:if test="${page eq i}">
                                            <li class="page-item active">
                                                <a class="page-link" href="DepositHistoryServlet?page=${i}">${i}</a> 
                                                <span class="sr-only">(current)</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${page != i}">
                                            <li class="page-item">
                                                <a class="page-link" href="DepositHistoryServlet?page=${i}">${i}</a> 
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${page >= total}">
                                        <li>
                                            <a class="page-link disabled" href="DepositHistoryServlet?page=${page+1}">></a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page != total and page < total}">
                                        <li>
                                            <a class="page-link" href="DepositHistoryServlet?page=${page+1}">></a>
                                        </li>
                                    </c:if>
                                </ul>
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
