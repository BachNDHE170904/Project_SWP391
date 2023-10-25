<%-- 
    Document   : NavBar
    Created on : Oct 11, 2023, 8:17:38 PM
    Author     : ADMIN
--%>

<%@page import="dal.UserDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User acc = null;
    //check if the user is logged in or not
    if (session.getAttribute("user") != null) {
        acc = (User) session.getAttribute("user");
    }
    UserDetails details = (UserDetails) session.getAttribute("userDetail");
    UserDAO db = new UserDAO();
%>
<nav class="navbar navbar-expand-md bg-body-tertiary ">
    <div class="container-fluid">
        <c:if test="${sessionScope.userDetail.roleId== 1}">
            <a class="navbar-brand" href="AdminDashBoard.jsp">Happy Programming</a>
        </c:if>
        <c:if test="${sessionScope.userDetail.roleId!= 1}">
            <a class="navbar-brand" href="WelcomePage.jsp">Happy Programming</a>
        </c:if>
        <button class="navbar-toggler ms-auto" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <%
                if (acc != null) {
            %>
            <div class="nav-item dropdown ms-auto">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <%
                        String avatarLink = db.getUserAvatar(acc.getUserId());
                        if (avatarLink == null || avatarLink.isEmpty()) {
                    %>
                    <img class="rounded-circle" alt="" src="img/default_avatar.jpg" style="width: 40px; height: 40px;" />
                    <% } else {%>
                    <img class="rounded-circle" alt="" src="<%=avatarLink%>" style="width: 40px; height: 40px;" />
                    <%}%>
                    <%= acc.getUsername()%>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                    <% if (acc != null) {%>
                    <li><a class="dropdown-item" href="ViewUserProfile.jsp">View my Profile</a></li>
                    <li><a class="dropdown-item" href="ChangePassword.jsp">Change Password</a></li>
                        <%if (details.getRoleId() == 3) {%>
                    <li><a class="dropdown-item" href="createRequest">Create Request</a></li>
                    <li><a class="dropdown-item" href="myRequest">List Request</a></li>
                        <%}
                            }%>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="LogOutServlet">Log Out</a></li>
                </ul>
            </div>
            <%
            } else {
            %>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Register.jsp">Register</a>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </div>
</nav>