/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
package Controller;

import DAO.RequestDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< Updated upstream
=======
import model.User;
import model.UserDetails;
>>>>>>> Stashed changes

/**
 *
 * @author ddtd2
 */
public class UpdateProfileServlet extends HttpServlet {
<<<<<<< Updated upstream
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
=======

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
>>>>>>> Stashed changes
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
<<<<<<< Updated upstream
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
=======
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
>>>>>>> Stashed changes
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
<<<<<<< Updated upstream
            out.println("<title>Servlet UpdateProfileServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
=======
            out.println("<title>Servlet UpdateProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
>>>>>>> Stashed changes
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
<<<<<<< Updated upstream
    throws ServletException, IOException {
        request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
=======
            throws ServletException, IOException {
        request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
>>>>>>> Stashed changes
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
<<<<<<< Updated upstream
    throws ServletException, IOException {
        // Lấy thông tin người dùng đã submit từ form
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob"); 
=======
            throws ServletException, IOException {
        // Lấy thông tin người dùng đã submit từ form
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob");
>>>>>>> Stashed changes
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
<<<<<<< Updated upstream
        
        // Khởi tạo đối tượng DAO 
        UserDAO userDAO = new UserDAO();
        
=======
        String proimage = request.getParameter("proimage");
// n?u e muôn tu lam thi a goi ý là o day co 1 cau dieu kien if cai current pass có gi?ng v?i pas trong dtb ko neus giong thi cho qua n?u khác thì báo l?i
        UserDetails user = (UserDetails) request.getSession().getAttribute("userDetail");
        User userEmail = (User) request.getSession().getAttribute("user");

        boolean changeFullname = false;
        if (!fullname.trim().equals(user.getFullname()) || !proimage.isEmpty()) {
            changeFullname = true;
        } else {
            changeFullname = false;

        }
        // Khởi tạo đối tượng DAO 
        UserDAO userDAO = new UserDAO();

>>>>>>> Stashed changes
        System.out.println(username);
        System.out.println(fullname);
        System.out.println(dob);
        System.out.println(gender);
        System.out.println(address);
        System.out.println(phone);
<<<<<<< Updated upstream
        System.out.println(currentPassword);
        System.out.println(newPassword);
        
        try {
            // Gọi hàm cập nhật thông tin người dùng
            userDAO.updateUser(username, fullname, dob, gender, address, phone, newPassword);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
          

=======
        System.out.println("currentPassword" + currentPassword);
        System.out.println(proimage);

        try {
            // Gọi hàm cập nhật thông tin người dùng
            userDAO.updateUser(username, fullname, dob, gender, address, phone, newPassword, currentPassword, changeFullname, proimage);
            UserDetails details = userDAO.getUserDetails(userEmail.getEmail());
            request.getSession().setAttribute("userDetail", details);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
>>>>>>> Stashed changes

        // Chuyển hướng người dùng đến trang hiển thị kết quả
        request.getRequestDispatcher("ViewUserProfile.jsp").forward(request, response);
    }

}
