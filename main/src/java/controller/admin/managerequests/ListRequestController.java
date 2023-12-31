/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.managerequests;

import controller.Common;
import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import dal.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;

@WebServlet(name = "ListRequestController", urlPatterns = {"/ListRequestController"})
public class ListRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDAO rd = new RequestDAO();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }

        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> statuses = statusDAO.getAll();
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        String key = Common.handleString(request.getParameter("key"));
        String statusStr = Common.handleString(request.getParameter("status"));
        int status = 0;
        if ("1".equals(statusStr)) {
            status = 1;
        } else if ("2".equals(statusStr)) {
            status = 2;
        } else if ("3".equals(statusStr)) {
            status = 3;
        } else if ("4".equals(statusStr)) {
            status = 4;
        }
        int allRequests = rd.countPagingRequests(key, status);
        int totalPage = allRequests / 10;
        if (allRequests % 10 != 0) {
            totalPage = totalPage + 1;
        }
        List<Request> lr = rd.getPagingRequests(page, key, status);

        request.setAttribute("statuses", statuses);
        request.setAttribute("pros", listPro);
        request.setAttribute("skills", skills);
        request.setAttribute("listRequests", lr);
        request.setAttribute("key", key);
        request.setAttribute("status", status);
        request.setAttribute("page", page);
        request.setAttribute("total", totalPage);
        request.setAttribute("ep", totalPage);
        request.getRequestDispatcher("admin/ListRequest.jsp").forward(request, response);
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
//        String search = request.getParameter("search");
//        RequestDAO dr = new RequestDAO();
//        List<Request> lr = dr.searchRequests(search);
//        request.setAttribute("listRequests", lr);
//        request.getRequestDispatcher("admin/ListRequest.jsp").forward(request, response);
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
