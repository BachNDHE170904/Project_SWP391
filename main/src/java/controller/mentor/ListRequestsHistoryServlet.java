package controller.mentor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import controller.Common;
import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import dal.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ListRequestsHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int currPage = Common.handleInt(request.getParameter("page"));
        RequestDAO requestDAO = new RequestDAO();
        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> statuses = statusDAO.getAll();
        int pageNum = Common.handleNum(requestDAO.countRequestByMentorIDHistory(user.getUserId()));
        List<Request> list = requestDAO.getRequestHistoryByMentorID(user.getUserId(), (currPage-1)*10, 10);
        
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();

        request.setAttribute("list", list);
        request.setAttribute("statuses", statuses);
        request.setAttribute("pros", listPro);
        request.setAttribute("skills", skills);
        request.setAttribute("currPage", (currPage < 1 ? 1 : currPage));
        request.setAttribute("pageNum", pageNum);
        request.getRequestDispatcher("mentor/ListRequestHistory.jsp").forward(request, response);
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
