/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import dal.MentorDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mentor;
import model.User;
import model.UserDetails;

/**
 *
 * @author trand
 */
public class ViewAllMentor extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> listMentor = mentorDAO.getAllMentors();
        request.setAttribute("listMentor", listMentor);
        request.getRequestDispatcher("ViewAllMentors.jsp").forward(request, response);
    }

}
