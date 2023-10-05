/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author admin
 */
public class ChangePassword extends HttpServlet {

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
    String username = request.getParameter("user");
    String opass = request.getParameter("opass");
    String p = request.getParameter("pass");
    String confirmPass = request.getParameter("rpass");
    
    UserDAO db = new UserDAO();
    User u = db.getUser(username);
    
    // Define a regular expression pattern to enforce password requirements
    String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=.*\\d).*";

    if (u == null || !u.getPass().equals(opass)) {
        String ms = "Old password is incorrect!";
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("change.jsp").forward(request, response);
    } else if (!p.equals(confirmPass)) {
        String ms = "New password and confirmation password do not match!";
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("change.jsp").forward(request, response);
    } else if (!p.matches(passwordPattern)) {
        String ms = "Password must contain at least one uppercase letter, one lowercase letter, one special character, and may or may not contain a number.";
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("change.jsp").forward(request, response);
    } else {
        User us = new User(username, p);
        db.change(us);
        HttpSession session = request.getSession();
        session.setAttribute("user", us);
        session.setAttribute("successMsg", "Change password successfully!");
        response.sendRedirect("WelcomePage.jsp");
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
