/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.mentee;

import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProgramingLanguage;
import model.Skill;
import model.User;

/**
 *
 * @author kienb
 */
public class CreateRequestServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SkillDAO skillDAO = new SkillDAO();
            ArrayList<Skill> skills = skillDAO.getActiveSkills();
            ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
            ArrayList<ProgramingLanguage> lists = programingLanguageDAO.getActiveProgramingLanguage();
            request.setAttribute("skills", skills);
            request.setAttribute("lists", lists);
            request.getRequestDispatcher("CreateRequest.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String title = request.getParameter("title");
            Date deadline = Date.valueOf(request.getParameter("deadline"));
            int languageId=Integer.parseInt(request.getParameter("language"));
            String[] skill = request.getParameterValues("selectedSkills");
            String content = request.getParameter("content");
            User user = (User) request.getSession().getAttribute("user");
            RequestDAO requestDAO = new RequestDAO();
            requestDAO.insertRequest(user.getUserId(), title, content, deadline, 1, skill,languageId );
            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreateRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
