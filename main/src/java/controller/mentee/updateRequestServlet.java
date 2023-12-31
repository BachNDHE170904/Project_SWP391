/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentee;

import dal.RequestDAO;
import dal.TransactionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import model.User;

/**
 *
 * @author ddtd2
 */
public class updateRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet updateRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateRequestServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        Date createdDate = Date.valueOf(LocalDate.now());
        Date deadline = Date.valueOf(request.getParameter("deadline"));
        String status = "1";
        long price = Integer.parseInt(request.getParameter("price"));
        String pro = request.getParameter("pro");
        String[] skills = request.getParameterValues("selectedSkills");
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user");

        TransactionDAO transactionDAO = new TransactionDAO();
        long balance = transactionDAO.getAccountBalanceByUserId(user.getUserId());
        RequestDAO requestDAO = new RequestDAO();
        int requestId = Integer.parseInt(id);
        long currentPrice = requestDAO.getPriceofRequest(requestId);
        if ((balance+currentPrice - price)>=0) {
            requestDAO.updateRequest(user.getUserId(), id, title, createdDate, deadline, status, pro, skills, content, price);
            transactionDAO.updateAcountBalance(user.getUserId(), (currentPrice - price));
            request.getSession().setAttribute("successMsg", "Your request is updated successfully!");
        } else {
            request.getSession().setAttribute("errorMsg", "You don't have enough money in your account!");
        }
        response.sendRedirect("myRequest");
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
