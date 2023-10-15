/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.managelanguages;

import common.Common;
import dal.ProgramingLanguageDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProgramingLanguage;

/**
 *
 * @author hihih
 */
public class ProgramLanguageServlet extends HttpServlet {

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

        String method = Common.handleString((String) request.getAttribute("method"));

        if ("add".equalsIgnoreCase(method)) {
            request.getRequestDispatcher("admin/AddManageProgramLanguage.jsp").forward(request, response);
        } else {
            if ("update".equalsIgnoreCase(method)) {
                //update
            }
            ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
            ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getProgramingLanguage();

            request.setAttribute("programingLanguages", programingLanguages);
            request.getRequestDispatcher("admin/ManageProgramLanguage.jsp").forward(request, response);
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
