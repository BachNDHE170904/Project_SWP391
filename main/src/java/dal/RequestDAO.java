/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Skill;

/**
 *
 * @author kienb
 */
public class RequestDAO extends BaseDAO<Skill> {

    public void insertRequest(int userID, String title, String content, Date deadline, int statusID, String[] skills, int languageID) {
        try {
            String sql = "INSERT INTO [dbo].[Requests]\n"
                    + "           ([userId])\n"
                    + "     VALUES\n"
                    + "           (?)";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userID);
            ptm.executeUpdate();
            int requestID = getLastRequest();
            String xSQL = "INSERT INTO [dbo].[RequestDetail]\n"
                    + "           ([requestId]\n"
                    + "           ,[title]\n"
                    + "           ,[requestContent]\n"
                    + "           ,[createdDate]\n"
                    + "           ,[deadline]\n"
                    + "           ,[statusId]"
                    + "           ,[mentorId] )\n"
                    + "     VALUES\n"
                    + "           (?,?,?,GETDATE(),?,?,?)";
            PreparedStatement xtm = connection.prepareStatement(xSQL);
            xtm.setInt(1, requestID);
            xtm.setString(2, title);
            xtm.setString(3, content);
            xtm.setDate(4, deadline);
            xtm.setInt(5, statusID);
            xtm.setInt(6, -1);
            xtm.executeUpdate();
            for (String i : skills) {
                String qSQL = "INSERT INTO [dbo].[requestSkillsChoices]\n"
                        + "           ([requestId]\n"
                        + "           ,[skillId]\n"
                        + "           ,[languageId])\n"
                        + "     VALUES\n"
                        + "           (?,?,?)";
                PreparedStatement qtm = connection.prepareStatement(qSQL);
                qtm.setInt(1, requestID);
                qtm.setInt(2, Integer.parseInt(i));
                qtm.setInt(3, languageID);
                qtm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getLastRequest() {
        try {
            String sql = "Select top 1 requestId from Requests order by requestId desc";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
