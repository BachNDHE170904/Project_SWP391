/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.mentee;

import dal.RequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CancelRequestController", urlPatterns = {"/cancelRequest"})
public class CancelRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        RequestDAO dbRequest = new RequestDAO();
        int id = Integer.parseInt(raw_id);
        dbRequest.updateRequestStatus(id, 3);
        response.sendRedirect("myRequest");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        RequestDAO dbRequest = new RequestDAO();
        int id = Integer.parseInt(raw_id);
        dbRequest.updateRequestStatus(id, 3);
        response.sendRedirect("myRequest");
    }

}
