/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.Common;
import dal.MentorDAO;
import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import dal.TransactionDAO;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.User;

@WebServlet(name = "RequestServlet", urlPatterns = {"/MentorRequestServlet"})
public class RequestServlet extends HttpServlet {

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
            String id = Common.handleString(request.getParameter("id"));
            int currPage = Common.handleInt(request.getParameter("page"));
            User user = (User) request.getSession().getAttribute("user");
            RequestDAO requestDAO = new RequestDAO();
            if (!id.isEmpty()) {
                String menteeName=request.getParameter("mentee");
                UserDAO userDAO=new UserDAO();
                User mentee = userDAO.getUserByUserName(menteeName);

                RequestDAO dbRequest = new RequestDAO();
                TransactionDAO transactionDAO = new TransactionDAO();
                long currentPrice = dbRequest.getPriceofRequest(Integer.parseInt(id));
                requestDAO.removeProposalsForRequest(Integer.parseInt(id));
                transactionDAO.updateAcountBalance(mentee.getUserId(), currentPrice);
                requestDAO.updateRequestStatus(Common.handleInt(id), 3);
            }
            ArrayList<Request> list = requestDAO.getRequestByMentorID(user.getUserId(), 2, Common.handlePaging(currPage));
            int pageNum = Common.handleNum(requestDAO.countRequestByMentorID(user.getUserId(), 2));

            ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
            ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
            SkillDAO skillDAO = new SkillDAO();
            ArrayList<Skill> skills = skillDAO.getActiveSkills();

            request.setAttribute("list", list);
            request.setAttribute("pros", listPro);
            request.setAttribute("skills", skills);
            request.setAttribute("currPage", (currPage < 1 ? 1 : currPage));
            request.setAttribute("pageNum", pageNum);

            request.getRequestDispatcher("mentor/ListRequest.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, e);
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
            request.getRequestDispatcher("mentor/ListRequest.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, e);
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
