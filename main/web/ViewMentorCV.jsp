
<%@page import="model.ProgramingLanguage"%>
<%@page import="dal.ProgramingLanguageDAO"%>
<%@page import="model.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.SkillDAO"%>
<%@page import="dal.MentorDAO"%>
<%@page import="model.UserDetails"%>
<%@page import="model.User"%>
<%@page import="dal.UserDAO"%>
<%@page import="model.Mentor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View CV of mentor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <style>
        .form-label {
            margin-bottom: -0.5rem;
        }
        .form-group{
            margin-bottom: 0.5rem;
        }
    </style>
    <body>
        <%
            try {
                int id = Integer.parseInt(request.getParameter("userId"));
                UserDAO userDAO = new UserDAO();
                MentorDAO mentorDAO = new MentorDAO();
                User mentor = userDAO.getUserByID(id);
                UserDetails mentorDetail = userDAO.getUserDetails(mentor.getEmail());
                String avatarLink = userDAO.getUserAvatar(id);
                Mentor mentorCV = mentorDAO.getMentorByUserID(id);

                SkillDAO skillDAO = new SkillDAO();
                ArrayList<Skill> skills = skillDAO.getActiveSkills();
                ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
                ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getActiveProgramingLanguage();
        %>
        <jsp:include page="NavBar.jsp"></jsp:include>
            <div class="container light-style flex-grow-1 container-p-y">
                <h4 class="font-weight-bold py-3 mb-4">
                <%= mentor.getUsername()%>'s CV
            </h4>
            <%
                if (avatarLink == null || avatarLink.isEmpty()) {
            %>
            <img class="img-thumbnail rounded float-start" alt="" src="img/default_avatar.jpg" />
            <% } else {%>
            <img class="img-thumbnail rounded float-start" alt="" src="<%=avatarLink%>" />
            <%}%>
            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <label id="error" style="color: red;margin-left: 25px;">${error}</label>
                    <label id="msg" style="color: green;margin-left: 25px;">${msg}</label>
                    <input type="hidden" value="${action}" name="action">
                    <hr class="border-light m-0">
                    <div class="card-body" style="margin: 0 30px;">
                        <div class="form-group">
                            <label class="form-label">Username <font color="red">*</font></label>
                            <label class="form-control mb-1" name="profession"><%=mentor.getUsername()%></label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Full name <font color="red">*</font></label>
                            <label class="form-control mb-1" name="profession"><%=mentorDetail.getFullname()%></label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">DOB <font color="red">*</font></label>
                            <label class="form-control mb-1" name="profession"><%=mentorDetail.getDob()%></label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Gender <font color="red">*</font></label>
                                <c:choose>
                                    <c:when test="<%=mentorDetail.isSex()%>">
                                        <c:set var="gender" value="Male"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="gender" value="Female"></c:set>
                                    </c:otherwise>
                                </c:choose>
                            <label class="form-control mb-1" name="profession">${gender}</label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Address <font color="red">*</font></label>
                            <label class="form-control mb-1" name="profession"><%=mentorDetail.getAddress()%></label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Profession <font color="red">*</font></label>
                            <label class="form-control mb-1" name="profession"><%=mentorCV.getProfession()%></label>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Profession introduction <font color="red">*</font></label>
                            <textarea class="form-control mb-1" name="profession" readonly><%=mentorCV.getProfessionInfo()%></textarea>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Skills <font color="red">*</font></label>
                                <%if (skills.isEmpty()) { %>
                            <label><font color="red">No Skill available!</font></label>
                            <ul>
                                <%} else {
                                    ArrayList<Integer> skillId = mentorCV.getSkillsId();
                                    for (Skill skill : skills) {
                                        if (skillId.contains(skill.getSkillId())) {%>
                                <div class="me-3">
                                    <li><%= skill.getSkillName()%></li>
                                </div>
                                <%}%>
                                <%}
                                    }%>
                            </ul>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Service description <font color="red">*</font></label>
                            <textarea class="form-control mb-1" name="profession" readonly><%=mentorCV.getServiceInfo()%></textarea>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Achievement description <font color="red">*</font></label>
                            <textarea class="form-control mb-1" name="profession" readonly><%=mentorCV.getAchivementInfo()%></textarea>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Programing (Framework) <font color="red">*</font></label>
                            <ul>
                                <%if (programingLanguages.isEmpty()) { %>
                                <label><font color="red">No Language available!</font></label>
                                    <%} else {
                                        ArrayList<Integer> languageId = mentorCV.getLanguageId();
                                        for (ProgramingLanguage language : programingLanguages) {
                                            if (languageId.contains(language.getLanguageId())) {%>
                                <div class="me-3">
                                    <li><%= language.getLanguageName()%></li>
                                </div>
                                <%}%>
                                <%}
                                    }%>
                            </ul>
                        </div>
                        <label id="validate" style="color: red"></label>
                    </div>
                </div>
                <jsp:include page="Comments.jsp">
                    <jsp:param name="mentorId" value="<%= mentorCV.getMentorId() %>" />
                </jsp:include>
            </div>
        </div>
        <%} catch (Exception e) {
                request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
            }
        %>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
