/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.Common;
import dal.ProgramingLanguageDAO;
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
import model.MentorRecommendation;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ListRequestSuggestionServlet extends HttpServlet {

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
            out.println("<title>Servlet ListRequestSuggestionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListRequestSuggestionServlet at " + request.getContextPath() + "</h1>");
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
        User user = (User) request.getSession().getAttribute("user");
        MentorRecommendation recommend = new MentorRecommendation();
        int currPage = Common.handleInt(request.getParameter("page"));
        if(currPage<=0)currPage=1;
        List<Request> list = recommend.RequestSuggestion(user.getUserId());
        int pageNum = Common.handleNum(list.size());
        List<Request>pagedList=new ArrayList<Request>();
        for(int i=(currPage-1)*10;i<list.size();i++){
            pagedList.add(list.get(i));
        }

        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> statuses = statusDAO.getAll();
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        request.setAttribute("list", pagedList);
        request.setAttribute("statuses", statuses);
        request.setAttribute("pros", listPro);
        request.setAttribute("skills", skills);
        request.setAttribute("currPage", (currPage < 1 ? 1 : currPage));
        request.setAttribute("pageNum", pageNum);
        request.getRequestDispatcher("mentor/ListRequestsSuggestion.jsp").forward(request, response);
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
