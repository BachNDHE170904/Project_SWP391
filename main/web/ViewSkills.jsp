<%-- 
    Document   : newjsp
    Created on : 16-Oct-2023, 03:41:06
    Author     : GIN
--%>

<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="dal.SkillDAO"%>
<%@page import="model.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .center {
                margin: auto;
                width: 50%;
                padding: 10px;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Skills</title>
    </head>
    <body>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <div class="center">
                <div class="bg-light rounded h-100 p-4">
                    <h6 class="mb-4">Available skills</h6>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Skill Name</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            SkillDAO skillDAO = new SkillDAO();
                            ArrayList<Skill> ls = skillDAO.getActiveSkills();
                            Collections.sort(ls, new Comparator<Skill>() {
                                @Override
                                public int compare(Skill s1, Skill s2) {
                                    return s1.getSkillName().compareToIgnoreCase(s2.getSkillName());
                                }
                            });
                            int i = 1;
                            for (Skill s : ls) {
                        %>
                        <tr>
                            <th scope="row"><%=i%></th>
                            <td><%=s.getSkillName()%></td>
                        </tr>
                        <% i++;
                            }%>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
