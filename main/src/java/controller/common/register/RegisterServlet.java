/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.register;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.sql.Date;
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
            gender = genderStr.compareToIgnoreCase("Male") == 0;
            UserDAO db = new UserDAO();
            User user = db.getUserByEmailOnly(email);
            User userByName = db.getUserByUserName(username);
            UserDetails ud=new UserDetails();
            ud.setPhone(phone);
            ud.setFullname(fullname);
            ud.setAddress(address);
            ud.setDob(dob);
            ud.setSex(gender);
            ud.setRoleId(2);
            ud.setUsername(username);
            ud.setPass(password);
            ud.setEmail(email);
            ud.setIsAuthorized(false);
            HttpSession session = request.getSession();
            if (user == null && userByName == null) // No account found
            {
                User u = new User(username, myHash, email, false);
                db.insertUser(u);
                u = db.getUser(email, myHash);
                int userId = u.getUserId();
                ud.setUserId(userId);
                db.insertUserDetails(ud);
                db.insertUserStatus(userId,"active");
                session.setAttribute("status", "Registered successfully !");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if (userByName != null) {
                ms = "Username is already taken";
                request.setAttribute("ms", ms);
                request.setAttribute("details", ud);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else //Account already existed
            {
                ms = "Account already existed";
                request.setAttribute("ms", ms);
                request.setAttribute("details", ud);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
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
