package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import DAO.UserDAO;
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
        String fullName = request.getParameter("fullname");
        UserDAO db = new UserDAO();
        User user = db.getUser(username);
        UserDetails details=db.getUserDetails(username);

        if (user != null)//login successfull
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userDetail", details);
            //                  tên bi?n bên trai la ten bien se duoc luu tren session nen khi goi chung ta se goi ten bien trong dau ngoac
            // ten bien bên phai la ten bien cua file servlet ko lien quan gì c? nên nay e goi nham ten
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
