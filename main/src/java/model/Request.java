/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ddtd2
 */
public class Request {
    private int id;
    private String title;
    private String content;
    private Date createDate;
    private Date deadline;
    private List<Skill> skills;
    private ProgramingLanguage pro;
    private Status status;
    private String userName;
    private int mentorId;
    public Request() {
    }

    public Request(int id, String title, String content, Date createDate, Date deadline, List<Skill> skills, ProgramingLanguage pro, Status status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.deadline = deadline;
        this.skills = skills;
        this.pro = pro;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public ProgramingLanguage getPro() {
        return pro;
    }

    public void setPro(ProgramingLanguage pro) {
        this.pro = pro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
    
}
