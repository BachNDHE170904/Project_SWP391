<%-- 
    Document   : newjsp
    Created on : 16-Oct-2023, 03:41:06
    Author     : GIN
--%>

<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.MentorDAO"%>
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
        <table id="sampleTable" class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">Accountname</th>
                    <th scope="col">Profession</th>
                    <th scope="col">Average Rating</th>
                    <th scope="col">Comments</th>
                    <th scope="col">View Mentor CV</th>
                </tr>
            </thead>
            <tbody>
                <% int i = 1;
                    UserDAO userDAO = new UserDAO();
                    MentorDAO mentorDAO = new MentorDAO();
                    for (Mentor m : list) {
                        ArrayList<Comment> comments = mentorDAO.getCommentsOfMentorByMentorId(m.getMentorId());
                %>
                <tr>
                    <th scope="row"><%=i%></th>
                    <td><%=m.getFullname()%></td>
                    <td><%=m.getUsername()%></td>
                    <td><%=m.getProfession()%></td>
                    <td><%=m.getAverageRating()%> </td>
                    <td><%=comments.size()%> Comments</td>
                    <td><a href="ViewMentorCV.jsp?mentorId=<%=m.getMentorId()%>">Detail</a></td>
                </tr>
                <%
                        i++;
                    }
                %>
            </tbody>
        </table>
        <script  src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="lib/fancytable/fancyTable.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $("#sampleTable").fancyTable({
                    /* Setting pagination or enabling */
                    pagination: true,
                    /* Rows per page kept for display */
                    perPage: 10,
                    globalSearch: true,
                    globalSearchExcludeColumns: [0,3,4,5,6]
                });

            });
        </script>
    </body>
</html>
