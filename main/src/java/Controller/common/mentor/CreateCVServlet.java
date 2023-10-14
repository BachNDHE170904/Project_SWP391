package controller.common.mentor;

import dal.ProgramingLanguageDAO;
import dal.SkillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Mentor;
import model.ProgramingLanguage;
import model.Skill;
import model.User;
import model.UserDetails;

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
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getActiveProgramingLanguage();

        request.setAttribute("skills", skills);
        request.setAttribute("programingLanguages", programingLanguages);
        request.getRequestDispatcher("mentor/CreateCV.jsp").forward(request, response);
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
        String profession = request.getParameter("profession");
        String profession_introduction = request.getParameter("profession_introduction");
        String service_description = request.getParameter("service_description");
        String achievement_description = request.getParameter("achievement_description");
        String[] skills = request.getParameterValues("skills");
        String[] programings = request.getParameterValues("programings");

        User user = (User) request.getSession().getAttribute("user");
        UserDetails details = (UserDetails) request.getSession().getAttribute("UserDetails");

        Mentor mentor = new Mentor();


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
