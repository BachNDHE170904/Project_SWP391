/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import dal.CommentDAO;
import dal.MentorDAO;
import dal.RequestDAO;
import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mentor;
import model.SendEmail;
import model.User;

/**
 *
 * @author ADMIN
 */
public class BidRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet BidRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BidRequestServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        UserDAO ud = new UserDAO();
        MentorDAO mentorDAO = new MentorDAO();
        Mentor mentor = mentorDAO.getMentorByUserID(user.getUserId());
        String menteeName = request.getParameter("menteeName");
        int id = Integer.parseInt(request.getParameter("id"));
        long price = Integer.parseInt(request.getParameter("price"));
        RequestDAO rd = new RequestDAO();
        if (rd.getProposalPriceForRequest(id, mentor.getMentorId())>0) {
            if (rd.updateProposalForRequest(id, price, mentor.getMentorId())) {
                session.setAttribute("successMsg", "Your offer is updated successfully!");
                response.sendRedirect("ListRequestSuggestionServlet");
                SendEmail sm = new SendEmail();
        
        String email = mentorDAO.getEmailByMentorId(mentor.getMentorId());
        String emailContent = "You just received a new offer from account "+ email + ". Please log in to Happy Programming to see detailed information" ;
        String toEmail = ud.getEmailByMenteeName(menteeName);
        String subject = "New offer";
        sm.sendEmail(toEmail, emailContent, subject);
            }
        } else {
            if (rd.insertProposalToRequest(id, price, mentor.getMentorId())) {
                session.setAttribute("successMsg", "Your offer is sent to mentee successfully!");
                response.sendRedirect("ListRequestSuggestionServlet");
                SendEmail sm = new SendEmail();
        String email = mentorDAO.getEmailByMentorId(mentor.getMentorId());
        String emailContent = "You just received a new offer from account "+ email + ". Please log in to Happy Programming to see detailed information" ;
        String toEmail = ud.getEmailByMenteeName(menteeName);
        String subject = "New offer";
        sm.sendEmail(toEmail, emailContent, subject);
            }
        }
    }
}
