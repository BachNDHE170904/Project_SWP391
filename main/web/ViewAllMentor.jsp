<%-- 
    Document   : ViewAllMentor
    Created on : Oct 18, 2023, 9:19:55 PM
    Author     : trand
--%>

<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Mentor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/ViewAllMentor.css">
        <title>Available Mentors</title>
    </head>
    <body>
        <% List<Mentor> list = (List<Mentor>) request.getAttribute("listMentor"); %>
        <h1>Available Mentors</h1>
        <table class="mentor-table">
            <thead>
                <tr>
                    <th>Order</th>
                    <th>ID</th>
                    <th>Fullname</th>
                    <th>Accountname</th>
                    <th>Profession</th>
                    <th>Rating</th>
                    <th>Comments</th>
                </tr>
            </thead>
            <tbody>
                <% for (Mentor m : list) {
                        UserDAO userDAO = new UserDAO();
                        User mentor = userDAO.getUserByID(m.getUserid());
                        UserDetails mentorDetail = userDAO.getUserDetails(mentor.getEmail());
                %>
                <tr>
                    <td><%=m.getMentorId()%></td>
                    <td><%=m.getMentorId()%></td>
                    <td><%=mentorDetail.getFullname()%></td>
                    <td><%=mentorDetail.getEmail()%></td>
                    <td><%=m.getProfession()%></td>
                    <td>4.5</td>
                    <td>Great mentor</td>
                    <td><a href="ViewMentorCV?mentorId=<%=m.getMentorId()%>">Detail</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
