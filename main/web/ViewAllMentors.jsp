<%-- 
    Document   : newjsp
    Created on : 16-Oct-2023, 03:41:06
    Author     : GIN
--%>

<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Mentor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/WelcomePageStyleIndex.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Mentors</title>
    </head>
    <body>
        <jsp:include page="NavBar.jsp"></jsp:include>
        <% List<Mentor> list = (List<Mentor>) request.getAttribute("listMentor"); %>
        <h1 class="text-center">Available Mentors</h1>
        <table class="table table-responsive table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ID</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">Accountname</th>
                    <th scope="col">Profession</th>
                    <th scope="col">Rating</th>
                    <th scope="col">Comments</th>
                    <th scope="col">View Mentor CV</th>
                </tr>
            </thead>
            <tbody>
                <% int i = 1;
                    for (Mentor m : list) {
                        UserDAO userDAO = new UserDAO();
                        User mentor = userDAO.getUserByID(m.getUserid());
                        UserDetails mentorDetail = userDAO.getUserDetails(mentor.getEmail());
                %>
                <tr>
                    <th scope="row"><%=i%></th>
                    <td><%=m.getMentorId()%></td>
                    <td><%=mentorDetail.getFullname()%></td>
                    <td><%=mentorDetail.getUsername()%></td>
                    <td><%=m.getProfession()%></td>
                    <td>4.5</td>
                    <td>Great mentor</td>
                    <td><a href="ViewMentorCV.jsp?userId=<%=mentor.getUserId()%>">Detail</a></td>
                </tr>
                <%
                    i++;
                    }
                %>
            </tbody>
        </table>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
