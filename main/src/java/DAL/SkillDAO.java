/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Skill;

/**
 *
 * @author ADMIN
 */
public class SkillDAO extends BaseDAO<Skill> {

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Skills,SkillStatus where Skills.skillStatusId=SkillStatus.skillStatusId \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setSkillId(rs.getInt("skillId"));
                s.setSkillName(rs.getString("skillName"));
                s.setSkillStatus(rs.getString("skillStatus"));
                skills.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skills;
    }

    public void insertSkill(Skill ski) {
        try {
            String sql = "insert into Skills(skillName,skillStatusId) values(?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql);
            int skillStatusId;
            statement.setString(1, ski.getSkillName());
            if (ski.getSkillStatus().equals("Active")) {
                skillStatusId = 1;
            } else {
                skillStatusId = 0;
            }
            statement.setInt(2, skillStatusId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
