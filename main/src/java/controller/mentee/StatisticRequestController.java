/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.mentee;

import dal.MentorDAO;
import dal.ProgramingLanguageDAO;
import dal.RequestDAO;
import dal.SkillDAO;
import dal.StatusDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import model.Mentor;
import model.MentorRecommendation;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;
import model.User;

@WebServlet(name = "StatisticRequestController", urlPatterns = {"/statisticRequest"})
public class StatisticRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MentorDAO mentorDAO = new MentorDAO();
        User user = (User) request.getSession().getAttribute("user");
        MentorRecommendation recommend = new MentorRecommendation();
        HashMap<Integer, List<Mentor>> suggestedMentors = new HashMap<>();
        RequestDAO requestDAO = new RequestDAO();
        List<Request> list = requestDAO.getRequestByID(user.getUserId());
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int allRequests = requestDAO.countRequestByUserIdWhichIsClosed(user.getUserId());
        request.setAttribute("allRequests", allRequests);
        int totalPage = allRequests / 10;
        if (allRequests % 10 != 0) {
            totalPage = totalPage + 1;
        }
        request.setAttribute("page", page);
        request.setAttribute("total", totalPage);
        request.setAttribute("ep", totalPage);
        list = requestDAO.getPagingRequestByIDWhichIsClosed(user.getUserId(), page);
        long totalDays = 0;
        for (Request re : list) {
            java.sql.Date createDate = re.getCreateDate();
            java.sql.Date deadline = re.getDeadline();

            // Convert java.sql.Date to Instant
            Instant createInstant = new Date(createDate.getTime()).toInstant();
            Instant deadlineInstant = new Date(deadline.getTime()).toInstant();

            LocalDate localCreateDate = createInstant.atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localDeadline = deadlineInstant.atZone(ZoneId.systemDefault()).toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(localCreateDate, localDeadline);
            totalDays += daysBetween;
        }
        request.setAttribute("totalDays", totalDays);

        Date currentDate = new Date();
        for (Request r : list) {
            Date deadline = r.getDeadline();
            if (r.getStatus().getId() == 2 && deadline.before(currentDate)) {
                requestDAO.updateRequestStatusToClosed(r.getId());
                r.setStatus(new Status(4, "Closed"));
            } else if (r.getStatus().getId() == 1 && r.getMentorId() == 0) {
                List<Mentor> suggestedlist = recommend.mentorSuggestionForMentee(r.getId());
                suggestedMentors.put(r.getId(), suggestedlist);
            }
            if (r.getMentorId() != 0) {
                r.setMentorUserName(mentorDAO.getMentorByMentorID(r.getMentorId()).getUsername());
                r.setMentorEmail(mentorDAO.getMentorByMentorID(r.getMentorId()).getEmail());
            }
        }
        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> statuses = statusDAO.getAll();
        ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
        ArrayList<ProgramingLanguage> listPro = programingLanguageDAO.getActiveProgramingLanguage();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Skill> skills = skillDAO.getActiveSkills();
        request.setAttribute("list", list);
        request.setAttribute("statuses", statuses);
        request.setAttribute("pros", listPro);
        request.setAttribute("skills", skills);
        request.setAttribute("suggestedMentorList", suggestedMentors);
        request.getRequestDispatcher("StatisticRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
