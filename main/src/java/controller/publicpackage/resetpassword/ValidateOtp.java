/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicpackage.resetpassword;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateOtp
 */
@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otpValue = request.getParameter("otp");
    
        if (!otpValue.matches("\\d+")) {
            request.setAttribute("message", "Wrong otp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
            return;
        }
        int value = Integer.parseInt(otpValue);
    
        HttpSession session=request.getSession();
        int otp=(int)session.getAttribute("otpCode");

        RequestDispatcher dispatcher = null;

        if (value == otp) {
            Cookie emailCookieRemove = new Cookie("email", "");
            emailCookieRemove.setMaxAge(0);
            response.addCookie(emailCookieRemove);
            Cookie passCookieRemove = new Cookie("password", "");
            passCookieRemove.setMaxAge(0);
            response.addCookie(passCookieRemove);
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("NewPassword.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("message", "Wrong otp");

            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);

        }

    }

}
