/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.changepassword;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
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
        try {
            HttpSession session = request.getSession();
            UserDAO db = new UserDAO();
            User u = (User) session.getAttribute("user");
            String username = u.getUsername();
            String opass = request.getParameter("opass");;
            String newPassword = request.getParameter("pass");
            String confirmPass = request.getParameter("rpass");
            String encryptedPassword = db.getEncryptedPassword(u.getEmail());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(opass.getBytes());
            byte[] digest = md.digest();
            String oldHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            md.reset();
            md.update(newPassword.getBytes());
            byte[] newPasswordDigest = md.digest();
            String encryptedNewPassword = DatatypeConverter.printHexBinary(newPasswordDigest).toUpperCase();
            // Define a regular expression pattern to enforce password requirements
            String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
            String message = null;
            if (u == null || !oldHash.equals(encryptedPassword)) {
                message = "Old password is incorrect!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else if (!newPassword.equals(confirmPass)) {
                message = "New password and confirmation password do not match!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else if (!newPassword.matches(passwordPattern)) {
                message = "Password must contain at least eight characters, at least one letter, one number and one special character.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } else {
                db.change(u.getEmail(), encryptedNewPassword);
                User us = db.getUserByUserName(username);
                Cookie emailCookieRemove = new Cookie("email", "");
                emailCookieRemove.setMaxAge(0);
                response.addCookie(emailCookieRemove);
                Cookie passCookieRemove = new Cookie("password", "");
                passCookieRemove.setMaxAge(0);
                response.addCookie(passCookieRemove);
                session.setAttribute("user", us);
                session.setAttribute("successMsg", "Change password successfully!");
                response.sendRedirect("WelcomePage.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
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
