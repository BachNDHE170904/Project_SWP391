
<%@page import="java.util.List"%>
<%@page import="model.Mentor"%>
<%@page import="dal.MentorDAO"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <link rel="stylesheet" href="css/WelcomePageStyleIndex.css">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <!-- Template Stylesheet -->
        <link href="css/WelcomePageStyleIndex.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <%
            String msg = (String) session.getAttribute("successMsg");
            if (msg != null) {%>

        <script>
            swal("Congrats", "<%= msg%>", "success");
        </script>

        <% session.removeAttribute("successMsg");
            }%>
        <jsp:include page="NavBar.jsp"></jsp:include>


            <!-- Service Start -->
            <div class="container-xxl py-4">
                <div class="container">
                    <div class="row g-4">
                        <div class="col-lg-6 col-sm-6 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="service-item text-center pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-graduation-cap text-primary mb-4"></i>
                                    <h5 class="mb-3">Skilled Mentors</h5>
                                    <a href="ViewAllMentorsServlet">See all available mentors</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-sm-6 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="service-item text-center pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-globe text-primary mb-4"></i>
                                    <h5 class="mb-3">Programming skills</h5>
                                    <a  href="ViewSkills.jsp">All programming skills we have to offer</a>
                                </div>
                            </div>
                        </div>
<!--                        <div class="col-lg-4 col-sm-6 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="service-item text-center pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-globe text-primary mb-4"></i>
                                    <h5 class="mb-3">Programming Languages</h5>
                                    <a  href="#">All programming languages we can support</a>
                                </div>
                            </div>
                        </div>-->
                    </div>
                </div>
            </div>
            <!-- Service End -->


            <!-- About Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="row g-5">
                        <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">
                            <div class="position-relative h-100">
                                <img class="img-fluid position-absolute w-100 h-100" src="img/about.jpg" alt="" style="object-fit: cover;">
                            </div>
                        </div>
                        <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                            <h6 class="section-title bg-white text-start text-primary pe-3">About Us</h6>
                            <h1 class="mb-4">Welcome to Happy Programming</h1>
                            <p class="mb-4">Our website allows mentors, mentees to connect with each others online</p>
                            <p class="mb-4">Our website also allows mentors to support mentees to learn programming</p>
                            <div class="row gy-2 gx-4 mb-4">
                                <div class="col-sm-6">
                                    <p class="mb-0" ><i class="fa fa-arrow-right text-primary me-2"></i>Skilled Mentors</p>
                                </div>
                                <div class="col-sm-6">
                                    <p class="mb-0" ><i class="fa fa-arrow-right text-primary me-2"></i>Programming skills</p>
                                </div>
                                <div class="col-sm-6">
                                    <p class="mb-0" ><i class="fa fa-arrow-right text-primary me-2"></i>Programming languages</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- About End -->


            <!-- Categories Start -->
            <section class="page-section" id="services">
                <div class="container">
                    <div class="text-center">
                        <h2 class="section-heading text-uppercase"> Skills</h2>
                        <h3 class="section-subheading text-muted">Some skills that we can train</h3>
                    </div>
                    <div class="row text-center">
                        <div class="col-md-4">
                            <span class="fa-stack fa-4x">
                                <i class="fas fa-circle fa-stack-2x text-primary"></i>
                                <i class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
                            </span>
                            <h4 class="my-3">Web develovement</h4>
                            <p class="text-muted">You can design a website quickly and easily to start a business, create a blog, or post your portfolio</p>
                        </div>
                        <div class="col-md-4">
                            <span class="fa-stack fa-4x">
                                <i class="fas fa-circle fa-stack-2x text-primary"></i>
                                <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                            </span>
                            <h4 class="my-3">Software develovement</h4>
                            <p class="text-muted">We can help you build software to meet your specific needs and achieve your business goals. Whether you require a custom application, a mobile app, a website, or a complex system, our team of experienced developers and designers can create a solution that perfectly aligns with your vision and requirements.</p>
                        </div>
                        <div class="col-md-4">
                            <span class="fa-stack fa-4x">
                                <i class="fas fa-circle fa-stack-2x text-primary"></i>
                                <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                            </span>
                            <h4 class="my-3">Game Development</h4>
                            <p class="text-muted">We can help you make a game that captivates and entertains your audience. Our team of skilled mentors can bring your gaming concept to life. From concept creation and game design to development and testing, we have the expertise to turn your ideas into a fully immersive and enjoyable gaming experience.</p>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Categories End -->

            <!-- Team Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Mentors</h6>
                        <h1 class="mb-5">Expert Mentors</h1>
                    </div>
                    <div class="row g-4">
                    <%
                        MentorDAO mentorDAO = new MentorDAO();
                        List<Mentor> listMentor = mentorDAO.getTop4ActiveMentors();
                        for (int i=0;i<listMentor.size();i++) {
                        Mentor m=listMentor.get(i);
                    %>
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="team-item bg-light">
                            <div class="overflow-hidden">
                                <img class="img-fluid" src="img/team-<%=i+1%>.jpg" alt="">
                            </div>
                            <div class="text-center p-4">
                                <h5 class="mb-0"><%= m.getFullname() %></h5>
                                <small>Profession:<%=m.getProfession()%></small>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <!-- Team End -->

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-6 col-md-6">
                        <h4 class="text-white mb-3">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, Ha Noi, Viet Nam</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>happyprogramming551@gmail.com</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    </body>
</html>
