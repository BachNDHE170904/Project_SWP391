/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.userauthorization;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ADMIN
 */
public class VerifyAccountServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            User acc = (User) session.getAttribute("user");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("otpCode")) {
                        // You've found the cookie by name
                        int correctOtp = Integer.parseInt(cookie.getValue());
                        int inputOtp = Integer.parseInt(request.getParameter("inputOtp"));
                        String ms;
                        if (correctOtp == inputOtp) {
                            UserDAO db = new UserDAO();
                            db.updateUserAuthorization(acc.getEmail());
                            db.updateUserRoleToMentee(acc.getUserId());
                            session.setAttribute("user", db.getUserByEmailOnly(acc.getEmail()));
                            session.setAttribute("userDetail", db.getUserDetails(acc.getEmail()));
                            session.setAttribute("successMsg", "Authorized successfully!");
                            Cookie otpCodeCookieRemove = new Cookie("otpCode", "");
                            otpCodeCookieRemove.setMaxAge(0);
                            response.addCookie(otpCodeCookieRemove);
                            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
                        } else {
                            ms = "Wrong otp";
                            request.setAttribute("ms", ms);
                            request.getRequestDispatcher("VerifyAccount.jsp").forward(request, response);
                        }
                        break; // If you've found the cookie, exit the loop
                    }
                }
            } else {
                request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("ms", "Invalid otp");
            request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);
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
