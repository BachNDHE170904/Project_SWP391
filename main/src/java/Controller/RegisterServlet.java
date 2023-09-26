/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Properties;
import model.SendEmail;
import model.User;
import model.UserDetails;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String fullname = request.getParameter("fullname");
        String genderStr = request.getParameter("gender");
        String address = request.getParameter("address");
        boolean gender;
        if (genderStr.compareToIgnoreCase("Male") == 0) {
            gender = true;
        } else {
            gender = false;
        }
        UserDAO db = new UserDAO();
        User user = db.getUser(email,password);
        if (user == null) // No account found
        {
            User u = new User(username, password, email, false);
            db.insertUser(u);
            u = db.getUser(email,password);
            int userId = u.getUserId();
            UserDetails ud = new UserDetails(phone, fullname, address, dob, gender, 4, username, password, userId, false);//4 means  role is User by default
            db.insertUserDetails(ud);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else //Account already existed
        {
            request.setAttribute("failedRegister", "fail");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
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
