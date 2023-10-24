package controller.publicpackage.resetpassword;

import dal.UserDAO;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        HttpSession mySession = request.getSession();
        UserDAO ud = new UserDAO();
        String message1 = null;
        boolean isEmailAsscociated = ud.isEmailAssociated(email);
        if (isEmailAsscociated) {
            if (email != null || !email.equals("")) {
                // Get the session object
                dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
                request.setAttribute("message", "OTP is sent to your email id");
                //request.setAttribute("connection", con);
                dispatcher.forward(request, response);
                request.setAttribute("status", "success");
            }

        } else {
            message1 = "Email is not linked to any account!";
            request.setAttribute("message1", message1);
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

}
