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

    public static void main(String[] args) {
        // Simulated mentor data with names, account names, total ratings, and average ratings
        MentorDAO db = new MentorDAO();
        List<Mentor> mentors = db.getAllMentors();

        // Simulated mentee
        String menteeName = "Mentee X";

        // Get mentor suggestions based on total ratings and average ratings
        List<Mentor> suggestedMentors = suggestMentorsBasedOnRatingsForMentee(mentors,1);

        System.out.println("Mentor Suggestions for " + menteeName + ":");
        for (Mentor mentor : suggestedMentors) {
            System.out.println("Full Name: " + mentor.getFullname());
            System.out.println("Account Name: " + mentor.getUsername());
            System.out.println("Total Ratings: " + mentor.getTotalRating());
            System.out.println("Average Rating: " + mentor.getAverageRating());
            System.out.println("Score: " + mentor.getScore());
            System.out.println("Invite Button");
            System.out.println();
        }
    }

    private static List<Mentor> suggestMentorsBasedOnRatingsForMentee(List<Mentor> mentors,int requestId) {
        MentorDAO mentorDAO = new MentorDAO();
        RequestDAO requestDAO=new RequestDAO();
        Request request=requestDAO.getRequestByRequestID(requestId);
        ArrayList<Integer>requestSkillIds=new ArrayList<>();
        for(Skill s:request.getSkills()){
            requestSkillIds.add(s.getSkillId());
        }
        // Calculate a score for each mentor based on a combination of total ratings and average rating
        for (Mentor mentor : mentors) {
            double totalRatingsFactor = 0.3; // Adjust as needed
            double averageRatingFactor = 0.7; // Adjust as needed
            double matchingSkillsFactor=0;
            if((mentor.getSkillsId().containsAll(requestSkillIds))&&mentor.getLanguageId().contains(request.getPro().getLanguageId()))matchingSkillsFactor=1;
            mentor.setTotalRating(mentorDAO.getTotalRatingOfMentorByMentorId(mentor.getMentorId()));
            double score = (totalRatingsFactor * mentor.getTotalRating() + averageRatingFactor * mentor.getAverageRating())*matchingSkillsFactor;
            mentor.setScore(score);
        }

        // Sort mentors by their scores in descending order
        mentors.sort((mentor1, mentor2) -> Double.compare(mentor2.getScore(), mentor1.getScore()));

        // Choose the top-rated mentors
        int numMentorsToSuggest = 3; // You can change this number
        List<Mentor> suggestedMentors = new ArrayList<>();
        for (int i = 0; i < numMentorsToSuggest && i < mentors.size(); i++) {
            if(mentors.get(i).getScore()>0) suggestedMentors.add(mentors.get(i));
        }

        return suggestedMentors;
    }
}
