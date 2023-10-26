<%-- 
    Document   : ViewSkill
    Created on : Oct 18, 2023, 3:45:10 PM
    Author     : trand
--%>

<%@page import="model.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/ViewList.css">
        <title>Danh sách khóa học</title>
    </head>
    <body>
        <% ArrayList<Skill> ls = (ArrayList<Skill>) request.getAttribute("listSkills");
            for (Skill s : ls) {
        %>
        <div class="course">
            <span class="course-id"><%=s.getSkillId()%></span>
            <h2 class="course-name"><%=s.getSkillName()%></h2>
            <% if(s.getSkillStatus().equals("1")){ %>
            <span class="course-status active">Active</span>
            <% }else{ %>
            <span class="course-status inactive">Inactive</span>
            <% } %>
        </div>
        <% }%>
</html>
