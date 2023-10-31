package controller.common.loginsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import model.User;
import model.UserDetails;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String rememberPass = request.getParameter("rememberPass");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();
            String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
            UserDAO db = new UserDAO();
            User user = db.getUser(email, myChecksum);
            String userStatus="inactive" ;
            if(user!=null)userStatus=db.getUserStatus(user.getUserId());
            UserDetails details = db.getUserDetails(email);
            HttpSession session = request.getSession();
            if (rememberPass != null && rememberPass.equals("true") && user != null)//remember pass
            {
                Cookie c_user = new Cookie("email", user.getEmail());
                Cookie c_pass = new Cookie("password", pass);
                c_user.setMaxAge(3600 * 24 * 30);
                c_pass.setMaxAge(3600 * 24 * 30);
                c_user.setPath("main/Login.jsp");
                c_pass.setPath("main/Login.jsp");
                response.addCookie(c_pass);
                response.addCookie(c_user);
            }
            if (user != null && details.getRoleId() != 1 && userStatus.equalsIgnoreCase("active"))//login successfull and is not admin
            {
                session.setAttribute("user", user);
                session.setAttribute("userDetail", details);
                request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
            } else if (user != null && details.getRoleId() == 1) {//login successfull and is admin
                session.setAttribute("user", user);
                session.setAttribute("userDetail", details);
                request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
            } else //login fail
            {
                request.setAttribute("failedLogin", "fail");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
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
