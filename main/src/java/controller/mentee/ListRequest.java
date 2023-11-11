/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentee;

import dal.MentorDAO;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Mentor;
import model.MentorRecommendation;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;
import model.User;

/**
 *
 * @author ddtd2
 */
@WebServlet(name = "ListRequest", urlPatterns = {"/myRequest"})
public class ListRequest extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListRequest at " + request.getContextPath() + "</h1>");
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
        MentorDAO mentorDAO = new MentorDAO();
        User user = (User) request.getSession().getAttribute("user");
        MentorRecommendation recommend = new MentorRecommendation();
        HashMap<Integer, List<Mentor>> suggestedMentors = new HashMap<>();
        RequestDAO requestDAO = new RequestDAO();
        List<Request> list = requestDAO.getRequestByID(user.getUserId());
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int allRequests = requestDAO.countRequestByUserId(user.getUserId());
        int totalPage = allRequests / 10;
        if (allRequests % 10 != 0) {
            totalPage = totalPage + 1;
        }
        request.setAttribute("page", page);
        request.setAttribute("total", totalPage);
        request.setAttribute("ep", totalPage);
        list = requestDAO.getPagingRequestByID(user.getUserId(), page);


        Date currentDate = new Date();
        for (Request r : list) {
            Date deadline = r.getDeadline();
            if (r.getStatus().getId() == 2 && deadline.before(currentDate)) {
                requestDAO.updateRequestStatusToClosed(r.getId());
                r.setStatus(new Status(4, "Closed"));
            } else if (r.getStatus().getId() == 1 && r.getMentorId() == 0) {
                List<Mentor> suggestedlist = recommend.mentorSuggestionForMentee(r.getId());
                suggestedMentors.put(r.getId(), suggestedlist);
            }
            if (r.getMentorId() != 0) {
                r.setMentorUserName(mentorDAO.getMentorByMentorID(r.getMentorId()).getUsername());
                r.setMentorEmail(mentorDAO.getMentorByMentorID(r.getMentorId()).getEmail());
            }
        }
        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> statuses = statusDAO.getAll();
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        request.setAttribute("list", list);
        request.setAttribute("statuses", statuses);
        request.setAttribute("pros", listPro);
        request.setAttribute("skills", skills);
        request.setAttribute("suggestedMentorList", suggestedMentors);
        request.getRequestDispatcher("ListRequest.jsp").forward(request, response);
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
