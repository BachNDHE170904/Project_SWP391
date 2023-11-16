/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.managelanguages;

import controller.Common;
import dal.ProgramingLanguageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProgramingLanguage;

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
        try {
            String action = Common.handleString(request.getParameter("action"));
            ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();

            if ("add".equalsIgnoreCase(action)) {
                request.setAttribute("action", action);
                request.getRequestDispatcher("admin/AddUpdateProgramLanguage.jsp").forward(request, response);
            } else if ("update".equalsIgnoreCase(action)) {
                int id = Common.handleInt(request.getParameter("id"));
                request.setAttribute("language", languageDAO.getProgramingLanguageById(id));
                request.setAttribute("action", action);
                request.getRequestDispatcher("admin/AddUpdateProgramLanguage.jsp").forward(request, response);
            } else {
                if ("update_status".equalsIgnoreCase(action)) {
                    int id = Common.handleInt(request.getParameter("id"));
                    if (id != 0) {
                        ProgramingLanguage language = languageDAO.getProgramingLanguageById(id);
                        if ("Active".equalsIgnoreCase(language.getLanguageStatus())) {
                            languageDAO.updateProgramingLanguageStatus(id, "Inactive");
                        } else {
                            languageDAO.updateProgramingLanguageStatus(id, "Active");
                        }
                    }
                }
                int currPage = Common.handleInt(request.getParameter("page"));
                String key = Common.handleString(request.getParameter("key"));
                String statusStr = Common.handleString(request.getParameter("status"));
                int status = -1;
                if ("0".equals(statusStr)) {
                    status = 0;
                } else if ("1".equals(statusStr)) {
                    status = 1;
                }

                ArrayList<ProgramingLanguage> programingLanguages = languageDAO.getProgramingLanguage(Common.handlePaging(currPage), key, status);
                int pageNum = Common.handleNum(languageDAO.countProgramingLanguage(key, status));
                request.setAttribute("programingLanguages", programingLanguages);
                request.setAttribute("currPage", (currPage < 1 ? 1 : currPage));
                request.setAttribute("pageNum", pageNum);
                request.setAttribute("key", key);
                request.setAttribute("status", status);
                request.getRequestDispatcher("admin/ListProgramLanguage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(ProgramLanguageServlet.class.getName()).log(Level.SEVERE, null, e);
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
            HttpSession session = request.getSession();
            String action = Common.handleString(request.getParameter("action"));
            String name = request.getParameter("name").trim();
            String status = request.getParameter("status");
            ProgramingLanguageDAO languageDAO = new ProgramingLanguageDAO();
            if (!name.trim().isEmpty()) {
                session.setAttribute("successMsg", "Language added successfully!");
                response.sendRedirect((request.getContextPath() + "/ProgramLanguageServlet"));
            } else {
                session.setAttribute("errorMsg", "Language added failed!");
                response.sendRedirect((request.getContextPath() + "/ProgramLanguageServlet"));
            }
            if ("add".equalsIgnoreCase(action)&& !name.trim().isEmpty()) {
                ProgramingLanguage language = new ProgramingLanguage();
                language.setLanguageName(name);
                language.setLanguageStatus(status);
                if (languageDAO.insertProgramingLanguage(language)) {
                    response.sendRedirect(request.getContextPath() + "/ProgramLanguageServlet");
                }
            }
            if ("update".equalsIgnoreCase(action)&& !name.trim().isEmpty()) {
                int id = Common.handleInt(request.getParameter("id"));
                ProgramingLanguage language = new ProgramingLanguage();
                language.setLanguageId(id);
                language.setLanguageName(name);
                language.setLanguageStatus(status);
                if (languageDAO.updateProgramingLanguage(language)) {
                    response.sendRedirect(request.getContextPath() + "/ProgramLanguageServlet");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ProgramLanguageServlet.class.getName()).log(Level.SEVERE, null, e);
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
