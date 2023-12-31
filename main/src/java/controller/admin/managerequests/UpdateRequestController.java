/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.managerequests;

import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import dal.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;
import model.User;

/**
 *
 * @author ddtd2
 */
@WebServlet(name = "UpdateRequestController", urlPatterns = {"/updateAdminRequest"})
public class UpdateRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        RequestDAO dbRequest = new RequestDAO();
        Request requestEdit = dbRequest.getRequestByRequestID(id);
        request.setAttribute("r", requestEdit);
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> lists = programingLanguageDAO.getActiveProgramingLanguage();
        StatusDAO dbStatus = new StatusDAO();
        ArrayList<Status> statuses = dbStatus.getAll();
        request.setAttribute("skills", skills);
        request.setAttribute("lists", lists);
        request.setAttribute("statuses", statuses);
        request.getRequestDispatcher("UpdateRequest.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String status = request.getParameter("statusId");
        RequestDAO requestDAO = new RequestDAO();
        if (requestDAO.updateRequestStatus(Integer.parseInt(id), Integer.parseInt(status))) {
            request.getSession().setAttribute("successMsg", "Your request is updated successfully!");
            response.sendRedirect("ListRequestController");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
