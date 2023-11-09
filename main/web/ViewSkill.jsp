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
    <% 
        int itemsPerPage = 5; // Number of items to display per page
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int startIndex = (currentPage - 1) * itemsPerPage;

        ArrayList<Skill> ls = (ArrayList<Skill>) request.getAttribute("listSkills");
        int totalItems = ls.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        for (int i = startIndex; i < Math.min(startIndex + itemsPerPage, totalItems); i++) {
            Skill s = ls.get(i);
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
    <% } %>

    <!-- Pagination -->
    <ul class="pagination">
        <% for (int i = 1; i <= totalPages; i++) { %>
            <li class="<%= (i == currentPage) ? "active" : "" %>">
                <a href="?page=<%= i %>"><%= i %></a>
            </li>
        <% } %>
    </ul>
</body>
</html>
