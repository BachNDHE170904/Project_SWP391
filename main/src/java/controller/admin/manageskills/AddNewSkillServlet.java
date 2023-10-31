/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.manageskills;

import dal.SkillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Skill;

/**
 *
 * @author ADMIN
 */
public class AddNewSkillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SkillDAO db = new SkillDAO();
        int skillId = Integer.parseInt(request.getParameter("skillId"));
        String skillName = request.getParameter("skillName");
        String status = request.getParameter("status");
        Skill newSkill = new Skill();
        newSkill.setSkillId(skillId);
        newSkill.setSkillName(skillName);
        newSkill.setSkillStatus(status);
        if (db.updateSkill(newSkill)) {
            request.getRequestDispatcher("AdminManageSkills.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SkillDAO db = new SkillDAO();
        String skillName = request.getParameter("skillName");
        String status = request.getParameter("status");
        Skill newSkill = new Skill();
        newSkill.setSkillName(skillName);
        newSkill.setSkillStatus(status);
        if (db.insertSkill(newSkill)) {
            request.getRequestDispatcher("AdminManageSkills.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
