/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Mentor {

    private int mentorId;
    private int userid;
    private int[] skillsId;
    private int[] languageId;
    private String profession;
    private String professionInfo;
    private String serviceInfo;
    private String achivementInfo;

    public Mentor() {
    }

    public Mentor(int mentorId, int userid, int[] skillsId, int[] languageId, String profession, String professionInfo, String serviceInfo, String achivementInfo) {
        this.mentorId = mentorId;
        this.userid = userid;
        this.skillsId = skillsId;
        this.languageId = languageId;
        this.profession = profession;
        this.professionInfo = professionInfo;
        this.serviceInfo = serviceInfo;
        this.achivementInfo = achivementInfo;
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

    public int[] getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(int[] skillsId) {
        this.skillsId = skillsId;
    }

    public int[] getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int[] languageId) {
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