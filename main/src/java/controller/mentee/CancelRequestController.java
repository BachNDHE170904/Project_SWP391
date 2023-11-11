/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.mentee;

import dal.RequestDAO;
import dal.TransactionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

@WebServlet(name = "CancelRequestController", urlPatterns = {"/cancelRequest"})
public class CancelRequestController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        User user = (User) request.getSession().getAttribute("user");

        RequestDAO dbRequest = new RequestDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        long currentPrice = dbRequest.getPriceofRequest(id);

        transactionDAO.updateAcountBalance(user.getUserId(), currentPrice);
        dbRequest.updateRequestStatus(id, 3);
        request.getSession().setAttribute("successMsg", "Your request is cancelled successfully!");
        response.sendRedirect("myRequest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
