<%-- 
    Document   : ViewAllMentor
    Created on : Oct 18, 2023, 9:19:55 PM
    Author     : trand
--%>

<%@page import="model.ProgramingLanguage"%>
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
        <title>Available Languages</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <% List<ProgramingLanguage> list = (List<ProgramingLanguage>) request.getAttribute("listLanguage"); %>
        <h1>Available Mentors</h1>
        <div id="table__paging">
            <table class="mentor-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Language Name</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ProgramingLanguage m : list) {
                    %>
                    <tr>
                        <td><%=m.getLanguageId()%></td>
                        <td><%=m.getLanguageName()%></td>
                    </tr>
                    <%
                        }
                    %>

                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">

            </ul>
        </nav>
    </body>
    <script>
        $(document).ready(function () {
            var itemsPerPage = 1; // Number of items to display per page
            var $tableContainer = $('#table__paging');
            var $table = $tableContainer.find('table');
            var $pagination = $('.pagination');

            var numRows = $table.find('tbody tr').length;
            var numPages = Math.ceil(numRows / itemsPerPage);

            // Create pagination links
            for (var i = 1; i <= numPages; i++) {
                var $li = $('<li class="page-item"><a class="page-link" href="#">' + i + '</a></li>');
                $li.data('page', i);
                $pagination.append($li);
            }

            // Show the first page and highlight its link
            $table.find('tbody tr:gt(' + (itemsPerPage - 1) + ')').hide();
            $pagination.find('li:first').addClass('active');

            // Handle pagination link click
            $pagination.find('li').click(function () {
                var $this = $(this);
                var page = $this.data('page');

                // Hide and show the appropriate rows
                var firstItem = (page - 1) * itemsPerPage;
                var lastItem = firstItem + itemsPerPage;

                $table.find('tbody tr').hide();
                $table.find('tbody tr:eq(' + (firstItem) + ')').show();
                $table.find('tbody tr:gt(' + (firstItem) + '):lt(' + (itemsPerPage - 1) + ')').show();

                // Highlight the clicked link
                $pagination.find('li').removeClass('active');
                $this.addClass('active');
            });
        });
    </script>
</html>
