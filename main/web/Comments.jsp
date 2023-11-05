<%-- 
    Document   : Comments
    Created on : Oct 30, 2023, 7:36:33 PM
    Author     : ADMIN
--%>

<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="model.User"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="dal.MentorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/Comments.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <section class="content-item" id="comments">
            <div class="container">   
                <div class="row">
                    <div class="col-sm-8">   
                        <%
                            int mentorId = Integer.parseInt(request.getParameter("mentorId"));
                            MentorDAO mentorDAO = new MentorDAO();
                            UserDAO userDAO = new UserDAO();
                            List<Comment> comments = mentorDAO.getCommentsOfMentorByMentorId(mentorId);
                            Collections.sort(comments, new Comparator<Comment>() {
                                @Override
                                public int compare(Comment s1, Comment s2) {
                                    return s2.getCreatedDate().compareTo(s1.getCreatedDate());
                                }
                            });
                        %>

                        <h3><%= comments.size()%> Comments</h3>
                        <!-- COMMENT  - START -->
                        <div class="media">
                            <div class="media-body">
                                <% for (Comment c : comments) {
                                        User u = userDAO.getUserByID(c.getUserId());
                                %>
                                <a class="pull-left" href="#"><img class="media-object" src="img/default_avatar.jpg" alt=""></a>
                                <h4 class="media-heading"><%= u.getUsername()%></h4>
                                <p><%= c.getCommentDetail()%></p>
                                <ul class="list-unstyled list-inline media-detail pull-left">
                                    <li><i class="fa fa-calendar"></i><%= c.getCreatedDate()%></li>
                                        <%for (int i = 1; i <= c.getRating(); i++) {%>
                                    <span class="fa fa-star checked"></span>
                                    <%}%>
                                    <%for (int i = 1; i <= 5 - c.getRating(); i++) {%>
                                    <span class="fa fa-star"></span>
                                    <%}%>
                                </ul>
                                <br>
                                <br>
                                <br>
                                <%}%>
                            </div>
                        </div>
                        <!-- COMMENT  - END -->
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
