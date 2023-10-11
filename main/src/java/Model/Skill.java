/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Skill {
    private int skillId;
    private String skillName;
    private String skillStatus;

    public Skill() {
    }

    public Skill(int skillId, String skillName, String skillStatus) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillStatus = skillStatus;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(String skillStatus) {
        this.skillStatus = skillStatus;
    }

    @Override
    public String toString() {
        return "Skill{" + "skillId=" + skillId + ", skillName=" + skillName + ", skillStatus=" + skillStatus + '}';
    }
    
}
