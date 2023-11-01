/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.MentorDAO;
import dal.RequestDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MentorRecommendation {
    public List<Mentor> mentorSuggestionForMentee(int requestId) {
        List<Mentor> suggestedMentors = new ArrayList<>();
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> mentors = mentorDAO.getAllActiveMentors();
        RequestDAO requestDAO = new RequestDAO();
        Request request = requestDAO.getRequestByRequestID(requestId);
        ArrayList<Integer> requestSkillIds = new ArrayList<>();
        for (Skill s : request.getSkills()) {
            requestSkillIds.add(s.getSkillId());
        }
        for (Mentor mentor : mentors) {
            if ((mentor.getSkillsId().containsAll(requestSkillIds)) && mentor.getLanguageId().contains(request.getPro().getLanguageId())) {
                suggestedMentors.add(mentor);
            }
        }
        return suggestedMentors;
    }
}
