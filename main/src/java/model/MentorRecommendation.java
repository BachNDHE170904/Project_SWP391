/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.MentorDAO;
import dal.RequestDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MentorRecommendation {

    public List<Mentor> mentorSuggestionForMentee(int requestId) {
        List<Mentor> suggestedMentors = new ArrayList<>();
        List<Mentor> ListOkMentors = new ArrayList<>();
        MentorDAO mentorDAO = new MentorDAO();
        List<Mentor> mentors = mentorDAO.getMentorsSuggestionByRequest(requestId);
        for (Mentor mentor : mentors) {
                int totalRatingScore = 0;
                if (mentor.getTotalRating() < 10) {
                    totalRatingScore = 1;
                } else if (mentor.getTotalRating() >= 10 && mentor.getTotalRating() <= 50) {
                    totalRatingScore = 2;
                } else {
                    totalRatingScore = 3;
                }
                double averageRatingScore = mentor.getAverageRating() * 2 * 0.7;
                mentor.setScore(totalRatingScore + averageRatingScore);
                ListOkMentors.add(mentor);
        }
        Collections.sort(ListOkMentors, new Comparator<Mentor>() {
            @Override
            public int compare(Mentor s1, Mentor s2) {
                return Double.compare(s2.getScore(), s1.getScore());
            }
        });
        int i = 0;
        while (suggestedMentors.size() <= 10 && i < ListOkMentors.size()) {
            suggestedMentors.add(ListOkMentors.get(i));
            i++;
        }
        return suggestedMentors;
    }

    public List<Request> RequestSuggestion(int userId) {
        MentorDAO mentorDAO = new MentorDAO();
        RequestDAO requestDAO = new RequestDAO();
        Mentor mentor = mentorDAO.getMentorByUserID(userId);
        List<Request> allRequests = requestDAO.getRequests();
        List<Request> suggestedRequests = new ArrayList<>();
        for (Request request : allRequests) {
            ArrayList<Integer> requestSkillIds = new ArrayList<>();
            for (Skill s : request.getSkills()) {
                requestSkillIds.add(s.getSkillId());
            }
            if (request.getStatus().getId() == 1 && request.getMentorId() == 0&&mentor.getSkillsId().containsAll(requestSkillIds)&&mentor.getLanguageId().contains(request.getPro().getLanguageId())) {
                suggestedRequests.add(request);
            }
        }
        return suggestedRequests;
    }
    public static void main(String[] args) {
        MentorRecommendation rec=new MentorRecommendation();
        System.out.println(rec.RequestSuggestion(6).size());
    }
}
