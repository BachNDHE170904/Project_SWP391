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
import jakarta.servlet.http.HttpSession;
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
        String skillName = request.getParameter("skillName").trim();
        String status = request.getParameter("status");
        Skill newSkill = new Skill();
        newSkill.setSkillId(skillId);
        newSkill.setSkillName(skillName);
        newSkill.setSkillStatus(status);
        int page = Integer.parseInt(request.getParameter("page"));
        if (!skillName.trim().isEmpty()) {
            db.updateSkill(newSkill);
        }
        request.getRequestDispatcher("AdminManageSkills.jsp?page=" + page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        SkillDAO db = new SkillDAO();
        String skillName = request.getParameter("skillName").trim();
        String status = request.getParameter("status");
        Skill newSkill = new Skill();
        newSkill.setSkillName(skillName);
        newSkill.setSkillStatus(status);
        int total = db.getTotalSkills();
        int page = (int) Math.ceil((double) (total) / 10);
        if (!skillName.trim().isEmpty()) {
            db.insertSkill(newSkill);
            session.setAttribute("successMsg", "Skill added successfully!");
        } else {
            session.setAttribute("errorMsg", "Skill added failed!");
        }
        request.getSession().removeAttribute("searchValue");
        request.getSession().removeAttribute("filterValue");
        request.getRequestDispatcher("AdminManageSkills.jsp?page=" + page).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
