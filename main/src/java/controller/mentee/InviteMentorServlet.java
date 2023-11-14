/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentee;

import dal.MentorDAO;
import dal.RequestDAO;
import dal.TransactionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SendEmail;
import model.User;

/**
 *
 * @author ADMIN
 */
public class InviteMentorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDAO requestDAO = new RequestDAO();
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int mentorId = Integer.parseInt(request.getParameter("mentorId"));
        long mentorPrice = Integer.parseInt(request.getParameter("mentorPrice"));
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();
        TransactionDAO transactionDAO = new TransactionDAO();
        MentorDAO mentorDAO = new MentorDAO();
        long balance = transactionDAO.getAccountBalanceByUserId(user.getUserId());
        long currentPrice = requestDAO.getPriceofRequest(requestId);
        
        if ((balance+currentPrice - mentorPrice)>=0) {
            if (requestDAO.setMentorIdForRequest(requestId, mentorId)) {
                requestDAO.setPriceForRequest(requestId, mentorPrice);
                transactionDAO.updateAcountBalance(user.getUserId(), (currentPrice - mentorPrice));
                requestDAO.removeProposalsForRequest(requestId);
                session.setAttribute("successMsg", "Your request is sent to mentor successfully!");
               SendEmail sm = new SendEmail();
        String emailContent = "You received a new invitation from account "+ username + ". Please log in to Happy Programming to see detailed information" ;
        String toEmail = mentorDAO.getEmailByMentorId(mentorId);
        String subject = "New invitation";
        sm.sendEmail(toEmail, emailContent, subject);
            }
        } else {
            request.getSession().setAttribute("errorMsg", "You don't have enough money in your account!");
        }
        response.sendRedirect("myRequest");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
