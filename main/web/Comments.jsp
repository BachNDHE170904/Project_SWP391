<%-- 
    Document   : Comments
    Created on : Oct 30, 2023, 7:36:33 PM
    Author     : ADMIN
--%>

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
                        %>

                        <h3><%= comments.size()%> Comments</h3>
                        <% for (Comment c : comments) {
                                User u = userDAO.getUserByID(c.getUserId());
                        %>
                        <!-- COMMENT  - START -->
                        <div class="media">
                            <a class="pull-left" href="#"><img class="media-object" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt=""></a>
                            <div class="media-body">
                                <h4 class="media-heading"><%= u.getUsername()%></h4>
                                <p><%= c.getCommentDetail()%></p>
                                <ul class="list-unstyled list-inline media-detail pull-left">
                                    <li><i class="fa fa-calendar"></i><%= c.getCreatedDate()%></li>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                </ul>
                            </div>
                        </div>
                        <!-- COMMENT  - END -->
                        <%}%>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
