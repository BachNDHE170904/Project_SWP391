/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.mentor;

import DAL.ProgramingLanguageDAO;
import Model.ProgramingLanguage;
import dal.SkillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Skill;
import model.User;

/**
 *
 * @author hihih
 */
public class CreateCV extends HttpServlet {

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
        ArrayList<Skill> skills = skillDAO.getSkills();
        ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getProgramingLanguage();

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
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("String");
        String email = request.getParameter("email");
        String genderStr = request.getParameter("gender");
        String address = request.getParameter("address");
        String profession = request.getParameter("profession");
        String profession_introduction = request.getParameter("profession_introduction");
        String service_description = request.getParameter("service_description");
        String achievement_description = request.getParameter("achievement_description");
        String[] skills = request.getParameterValues("skills");
        String[] programings = request.getParameterValues("programings");

        boolean gender = genderStr.compareToIgnoreCase("Male") == 0;
        User user = (User) request.getSession().getAttribute("user");
        Ment

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
