
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Create CV of mentor</title>
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
            <jsp:include page="../NavBar.jsp"></jsp:include>
                <div class="container light-style flex-grow-1 container-p-y">
                    <h4 class="font-weight-bold py-3 mb-4">
                    <c:choose>
                        <c:when test="${'update'.equals(action)}">
                            Detail CV
                        </c:when>
                        <c:otherwise>
                            Create CV
                        </c:otherwise>
                    </c:choose>
                </h4>
                <div class="card overflow-hidden">
                    <div class="row no-gutters row-bordered row-border-light">
                        <label id="error" style="color: red;margin-left: 25px;">${error}</label>
                        <label id="msg" style="color: green;margin-left: 25px;">${msg}</label>
                        <form action="CreateCV" method="POST" >
                            <input type="hidden" value="${action}" name="action">
                            <hr class="border-light m-0">
                            <div class="card-body" style="margin: 0 30px;">
                                <div class="form-group">
                                    <label class="form-label">Username <font color="red">*</font></label>
                                    <label class="form-control mb-1" name="profession">${sessionScope.user.getUsername()}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Full name <font color="red">*</font></label>
                                    <label class="form-control mb-1" name="profession">${sessionScope.userDetail.getFullname()}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">DOB <font color="red">*</font></label>
                                    <label class="form-control mb-1" name="profession">${sessionScope.userDetail.getDob()}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Gender <font color="red">*</font></label>
                                        <c:choose>
                                            <c:when test="${sessionScope.userDetail.isSex()}">
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
                                    <label class="form-control mb-1" name="profession">${sessionScope.userDetail.getAddress()}</label>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Profession <font color="red">*</font></label>
                                    <input type="text" class="form-control mb-1" name="profession" required <c:if test="${'update'.equals(action)}">value="${mentor.getProfession()}"</c:if>>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Profession introduction <font color="red">*</font></label>
                                        <textarea class="form-control mb-1" name="profession_introduction" required><c:if test="${'update'.equals(action)}">${mentor.getProfessionInfo()}</c:if></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Skills <font color="red">*</font></label>
                                        <div class="d-flex flex-wrap">
                                        <c:choose>
                                            <c:when test="${skills.isEmpty()}">
                                                <label><font color="red">No Skill available!</font></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${'update'.equals(action)}">
                                                        <c:set var="skillIds" value="${mentor.getSkillsId()}"></c:set>
                                                    </c:if>
                                                    <c:forEach items="${skills}" var="skill">
                                                        <c:choose>
                                                            <c:when test="${'update'.equals(action) && skillIds.contains(skill.getSkillId())}">
                                                            <div class="me-3">
                                                                <input id="skill${skill.getSkillId()}" type="checkbox"  value="${skill.getSkillId()}" name="skills" checked>
                                                                <label for="skill${skill.getSkillId()}">${skill.getSkillName()}</label>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="me-3">
                                                                <input id="skill${skill.getSkillId()}" type="checkbox"  value="${skill.getSkillId()}" name="skills">
                                                                <label for="skill${skill.getSkillId()}">${skill.getSkillName()}</label>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Service description <font color="red">*</font></label>
                                    <textarea class="form-control mb-1" name="service_description" required><c:if test="${'update'.equals(action)}">${mentor.getServiceInfo()}</c:if></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Achievement description <font color="red">*</font></label>
                                        <textarea class="form-control mb-1" name="achievement_description" required><c:if test="${'update'.equals(action)}">${mentor.getAchivementInfo()}</c:if></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Programing (Framework) <font color="red">*</font></label>
                                        <div class="d-flex flex-wrap">
                                        <c:choose>
                                            <c:when test="${programingLanguages.isEmpty()}">
                                                <label><font color="red">No Programing Language available!</font></label>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${'update'.equals(action)}">
                                                    <c:set var="languageIds" value="${mentor.getLanguageId()}"></c:set>
                                                </c:if>
                                                <c:forEach items="${programingLanguages}" var="programingLanguage">
                                                    <c:choose>
                                                        <c:when test="${'update'.equals(action) && languageIds.contains(programingLanguage.getLanguageId())}">
                                                        <div class="me-3">
                                                            <input id="language${programingLanguage.getLanguageId()}" type="checkbox"  value="${programingLanguage.getLanguageId()}" name="programingLanguages" checked>
                                                            <label for="language${programingLanguage.getLanguageId()}">${programingLanguage.getLanguageName()}</label>
                                                        </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="me-3">
                                                                <input id="language${programingLanguage.getLanguageId()}"  type="checkbox"  value="${programingLanguage.getLanguageId()}" name="programingLanguages">
                                                                <label for="language${programingLanguage.getLanguageId()}">${programingLanguage.getLanguageName()}</label>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <input type="submit" value="OK" class="btn btn-primary" id="create-cv">
                                <label id="validate" style="color: red"></label>
                            </div>
                        </form>
                    </div> 
                </div>
            </div>

            <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
            <script>
                $(document).ready(function () {
                    $('#create-cv').click(function () {
                        if (!$("input[name=skills]:checked").length) {
                            $('#validate').text("You must check at least one skills.");
                            return false;
                        } else if (!$("input[name=programingLanguages]:checked").length) {
                            $('#validate').text("You must check at least one programing languages.");
                            return false;
                        }
                    });
                });
            </script>
        </body>
    </html>
