/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.register;

import dal.UserDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Properties;
import model.SendEmail;
import model.User;
import model.UserDetails;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Date dob = Date.valueOf(request.getParameter("dob"));
            String fullname = request.getParameter("fullname");
            String genderStr = request.getParameter("gender");
            String address = request.getParameter("address");
            boolean gender;
            String ms;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            if (genderStr.compareToIgnoreCase("Male") == 0) {
                gender = true;
            } else {
                gender = false;
            }
            UserDAO db = new UserDAO();
            User user = db.getUserByEmailOnly(email);
            User userByName = db.getUserByUserName(username);
            HttpSession session = request.getSession();
            if (user == null && userByName == null) // No account found
            {
                User u = new User(username, myHash, email, false);
                db.insertUser(u);
                u = db.getUser(email, myHash);
                int userId = u.getUserId();
                UserDetails ud = new UserDetails(phone, fullname, address, dob, gender, 2, username, myHash, email, userId, false);//2 means  role is User by default
                db.insertUserDetails(ud);
                db.insertUserStatus(userId,"active");
                session.setAttribute("status", "Registered successfully !");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if (userByName != null) {
                ms = "Username is already taken";
                request.setAttribute("ms", ms);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else //Account already existed
            {
                ms = "Account already existed";
                request.setAttribute("ms", ms);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
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
