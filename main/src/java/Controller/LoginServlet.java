package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
        String username = request.getParameter("username");
        String rememberPass = request.getParameter("rememberPass");
        UserDAO db = new UserDAO();
        User user = db.getUser(username);
        UserDetails details=db.getUserDetails(username);
        if (rememberPass != null && rememberPass.equals("true"))//remember pass
        {
            Cookie c_user = new Cookie("username", user.getUsername());
            Cookie c_pass = new Cookie("password", user.getPass());
            c_user.setMaxAge(3600 * 24 * 30);
            c_pass.setMaxAge(3600 * 24 * 30);
            response.addCookie(c_pass);
            response.addCookie(c_user);
        }
        if (user != null)//login successfull
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userDetail", details);
            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
        } else //login fail
        {
            request.setAttribute("failedLogin", "fail");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
