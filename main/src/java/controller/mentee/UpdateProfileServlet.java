/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.mentee;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import model.User;
import model.UserDetails;

/**
 *
 * @author kienb
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class UpdateProfileServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            User user = (User) request.getSession().getAttribute("user");
            UserDAO udao = new UserDAO();
            UserDetails userDetail = udao.getUserDetails(user.getEmail());
            String avatar = udao.getUserAvatar(user.getUserId());
            request.setAttribute("avatar", avatar);
            request.setAttribute("detail", userDetail);
            request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String avatar = uploadFile(request);
        if(avatar.equals("default.file") || avatar.isEmpty()){
            avatar = userDAO.getUserAvatar(user.getUserId());
        }
        userDAO.updateUserDetail(user.getUserId(),username,fullname,phone,address,sex.equals("1"),dob,avatar);
        request.getSession().setAttribute("user", userDAO.getUserByID(user.getUserId()));
        request.getSession().setAttribute("userDetail", userDAO.getUserDetails(user.getEmail()));
        response.sendRedirect("ViewUserProfile.jsp");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.file";
    }
    
    public String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        int length = getServletContext().getRealPath("/").length();
        String uploadPath = new StringBuilder(getServletContext().getRealPath("/")).delete(length - 10, length - 4).toString() + File.separator + "img";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            for (Part part : request.getParts()) {
                String temp = getFileName(part);
                if (!temp.equals("default.file") && !temp.trim().isEmpty()) {
                    fileName = temp;
                    part.write(uploadPath + File.separator + fileName);
                    break;
                }
            }
        } catch (FileNotFoundException fne) {
        }
        return fileName;
    }

}
