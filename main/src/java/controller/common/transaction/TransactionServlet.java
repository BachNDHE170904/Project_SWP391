/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.transaction;

import dal.TransactionDAO;
import dal.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Transaction;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TransactionServlet", urlPatterns = {"/transaction"})
public class TransactionServlet extends HttpServlet {

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
            out.println("<title>Servlet TransactionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransactionServlet at " + request.getContextPath() + "</h1>");
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
        if ("00".equals(request.getParameter("status"))) {
            HttpSession session = request.getSession();
            TransactionDAO td = new TransactionDAO();
            User acc = null;
            //check if the user is logged in or not
            if (session.getAttribute("user") != null) {
                acc = (User) session.getAttribute("user");
            }
            long amount = Integer.parseInt(request.getParameter("amount"));
            String dateString = request.getParameter("createdDate");
            // Define the date format of the retrieved string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            // Parse the string into a java.util.Date object
            java.util.Date utilDate = null;
            try {
                utilDate = dateFormat.parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(TransactionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Convert java.util.Date to java.sql.Date
            Date sqlDate = new Date(utilDate.getTime());

            Transaction tran = new Transaction();
            tran.setTransactionId(request.getParameter("transactionId"));
            tran.setAmount(amount);
            tran.setContent(request.getParameter("content"));
            tran.setCreatedDate(sqlDate);
            tran.setUserId(acc.getUserId());
            if (td.insertTransaction(tran)) {
                if (!td.insertAcountBalance(acc.getUserId(), amount)) {
                    td.updateAcountBalance(acc.getUserId(), amount);
                }
            }
        }
        response.sendRedirect("WelcomePage.jsp");
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
