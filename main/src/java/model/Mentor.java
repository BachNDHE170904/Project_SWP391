/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Mentor extends UserDetails {

    private int mentorId;
    private int userid;
    private ArrayList<Integer> skillsId;
    private ArrayList<Integer> languageId;
    private String profession;
    private String professionInfo;
    private String serviceInfo;
    private String achivementInfo;
    private int totalRating;
    private double averageRating;

    public Mentor() {
    }

    public Mentor(int mentorId, int userid, ArrayList<Integer> skillsId, ArrayList<Integer> languageId, String profession, String professionInfo, String serviceInfo, String achivementInfo, int totalRating, double averageRating, String phone, String fullname, String address, Date dob, boolean sex, int roleId, String username, String pass, String email, int userId, boolean isAuthorized, String status) {
        super(phone, fullname, address, dob, sex, roleId, username, pass, email, userId, isAuthorized, status);
        this.mentorId = mentorId;
        this.userid = userid;
        this.skillsId = skillsId;
        this.languageId = languageId;
        this.profession = profession;
        this.professionInfo = professionInfo;
        this.serviceInfo = serviceInfo;
        this.achivementInfo = achivementInfo;
        this.totalRating = totalRating;
        this.averageRating = averageRating;
    }

    public Mentor(int mentorId, int userid, ArrayList<Integer> skillsId, ArrayList<Integer> languageId, String profession, String professionInfo,
            String serviceInfo,
            String achivementInfo) {
        this.mentorId = mentorId;
        this.userid = userid;
        this.skillsId = skillsId;
        this.languageId = languageId;
        this.profession = profession;
        this.professionInfo = professionInfo;
        this.serviceInfo = serviceInfo;
        this.achivementInfo = achivementInfo;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public ArrayList<Integer> getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(ArrayList<Integer> skillsId) {
        this.skillsId = skillsId;
    }

    public ArrayList<Integer> getLanguageId() {
        return languageId;
    }

    public void setLanguageId(ArrayList<Integer> languageId) {
        this.languageId = languageId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionInfo() {
        return professionInfo;
    }

    public void setProfessionInfo(String professionInfo) {
        this.professionInfo = professionInfo;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getAchivementInfo() {
        return achivementInfo;
    }

    public void setAchivementInfo(String achivementInfo) {
        this.achivementInfo = achivementInfo;
    }

}
