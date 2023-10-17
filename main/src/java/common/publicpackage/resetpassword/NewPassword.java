/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicpackage.resetpassword;

import dal.UserDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class NewPassword
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String newPassword = request.getParameter("password");
            String confPassword = request.getParameter("confPassword");
            RequestDispatcher dispatcher = null;
            String message = null;
            UserDAO ud = new UserDAO();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(newPassword.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

            if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {

                if (newPassword.matches(passwordPattern)) {
                    String email = (String) session.getAttribute("email");
                    boolean passwordUpdated = ud.updatePassword(email, myHash);
                    if (passwordUpdated) {
                        session.setAttribute("status", "Reset password successfully");
                        response.sendRedirect("Login.jsp");
                    } else {
                        request.setAttribute("status", "Password reset failed");
                        dispatcher = request.getRequestDispatcher("NewPassword.jsp");
                    }
                } else {
                    message = "Password must contain at least eight characters, at least one letter, one number and one special character.";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
                }
            }
            if (newPassword != null && confPassword != null && !newPassword.equals(confPassword)) {
                message = "New password and confirmation password do not match!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("NewPassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
