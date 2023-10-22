package controller.mentor;

import dal.MentorDAO;
import dal.ProgramingLanguageDAO;
import dal.SkillDAO;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Mentor;
import model.ProgramingLanguage;
import model.Skill;
import model.User;

@WebServlet("/CreateCV")
public class CreateCVServlet extends HttpServlet {

        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try {
                User user = (User) request.getSession().getAttribute("user");

                SkillDAO skillDAO = new SkillDAO();
                ArrayList<Skill> skills = skillDAO.getActiveSkills();

                ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
                ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getActiveProgramingLanguage();

                MentorDAO mentorDAO = new MentorDAO();
                Mentor mentor = mentorDAO.getMentorByUserID(user.getUserId());
                if (mentor.getMentorId() != 0) {
                    request.setAttribute("mentor", mentor);
                    request.setAttribute("action", "update");
                } else {
                    request.setAttribute("action", "add");
                }
                request.setAttribute("skills", skills);
                request.setAttribute("programingLanguages", programingLanguages);
                request.getRequestDispatcher("mentor/CreateCV.jsp").forward(request, response);
            } catch (Exception e) {
                Logger.getLogger(CreateCVServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try {
                String action = request.getParameter("action");
                String profession = request.getParameter("profession");
                String profession_introduction = request.getParameter("profession_introduction");
                String service_description = request.getParameter("service_description");
                String achievement_description = request.getParameter("achievement_description");
                String[] skills = request.getParameterValues("skills");
                String[] programings = request.getParameterValues("programingLanguages");

                User user = (User) request.getSession().getAttribute("user");

                UserDAO userDAO=new UserDAO();
                MentorDAO mentorDAO = new MentorDAO();

                if ("update".equals(action)) {
                    Mentor mentorDB = mentorDAO.getMentorByUserID(user.getUserId());
                    mentorDB.setSkillsId((ArrayList<Integer>) Arrays.stream(skills).map(Integer::valueOf).collect(Collectors.toList()));
                    mentorDB.setLanguageId((ArrayList<Integer>) Arrays.stream(programings).map(Integer::valueOf).collect(Collectors.toList()));
                    mentorDB.setProfession(profession);
                    mentorDB.setProfessionInfo(profession_introduction);
                    mentorDB.setServiceInfo(service_description);
                    mentorDB.setAchivementInfo(achievement_description);
                    if (mentorDAO.updateMentor(mentorDB)) {
                        request.setAttribute("msg", "Update success!");
                    } else {
                        request.setAttribute("error", "Update failed!");
                    }
                } else {
                    Mentor mentor = new Mentor();
                    mentor.setUserid(user.getUserId());
                    mentor.setSkillsId((ArrayList<Integer>) Arrays.stream(skills).map(Integer::valueOf).collect(Collectors.toList()));
                    mentor.setLanguageId((ArrayList<Integer>) Arrays.stream(programings).map(Integer::valueOf).collect(Collectors.toList()));
                    mentor.setProfession(profession);
                    mentor.setProfessionInfo(profession_introduction);
                    mentor.setServiceInfo(service_description);
                    mentor.setAchivementInfo(achievement_description);
                    Mentor mentorDB = mentorDAO.getMentorByUserID(user.getUserId());
                    if (mentorDB.getMentorId() == 0) {
                        int mentorId = mentorDAO.insertMentor(mentor);
                        if (mentorId == 0) {
                            request.setAttribute("error", "Insert failed!->" + mentorId);
                        } else {
                            userDAO.updateMenteeRoleToMentor(user.getUserId());
                            request.setAttribute("msg", "Insert success!->" + mentorId);
                        }
                    }
                }
                request.setAttribute("action", "update");
                doGet(request, response);
            } catch (Exception e) {
                Logger.getLogger(CreateCVServlet.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

}
