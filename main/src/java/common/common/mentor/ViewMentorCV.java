/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package common.common.mentor;

import dal.MentorDAO;
import dal.ProgramingLanguageDAO;
import dal.SkillDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mentor;
import model.ProgramingLanguage;
import model.Skill;
import model.User;
import model.UserDetails;

/**
 *
 * @author trand
 */
public class ViewMentorCV extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewMentorCV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewMentorCV at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            int id = Integer.parseInt(request.getParameter("mentorId"));
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
            ArrayList<Integer> skillId = mentorCV.getSkillsId();
            ArrayList<Integer> languageId = mentorCV.getLanguageId();
            request.setAttribute("mentor", mentor);
            request.setAttribute("mentorDetail", mentorDetail);
            request.setAttribute("avatarLink", avatarLink);
            request.setAttribute("mentorCV", mentorCV);
            request.setAttribute("skills", skills);
            request.setAttribute("programingLanguages", programingLanguages);
            request.setAttribute("skillId", skillId);
            request.setAttribute("languageId", languageId);
            request.getRequestDispatcher("ViewMentorCV.jsp").forward(request, response);
        } catch (Exception e) {
            processRequest(request, response);
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
        processRequest(request, response);
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
