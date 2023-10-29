<%-- 
    Document   : DashBoardSideBar
    Created on : Oct 11, 2023, 9:52:29 PM
    Author     : ADMIN
--%>

<%@page import="dal.UserDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //check if the user is logged in or not
    User acc = (User) session.getAttribute("user");
    UserDetails details = (UserDetails) session.getAttribute("userDetail");
    UserDAO db = new UserDAO();
%>
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-light navbar-light">
        <div class="d-flex align-items-center ms-4 mb-4">
            <div class="position-relative">
                <%
                    String avatarLink = db.getUserAvatar(acc.getUserId());
                    if (avatarLink == null || avatarLink.isEmpty()) {
                %>
                <img class="rounded-circle" alt="" src="img/default_avatar.jpg" style="width: 40px; height: 40px;" />
                <% } else {%>
                <img class="rounded-circle" alt="" src="<%=avatarLink%>" style="width: 40px; height: 40px;" />
                <%}%>
                <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
            </div>
            <div class="ms-3">
                <h6 class="mb-0">Admin</h6>
                <span>Admin</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="AdminDashBoard.jsp" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>
            <a href="AdminManageUsers.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Manage Users</a>
            <a href="AdminManageSkills.jsp" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>Manage Skills</a>
            <a href="ProgramLanguageServlet" class="nav-item nav-link"><i class="fa fa-code me-2"></i>Manage Program Language</a>
            <a href="AdminManageMentor.jsp" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Manage Mentors</a>
            <a href="ListRequestController" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Manage Requests</a>
        </div>
    </nav>
</div>