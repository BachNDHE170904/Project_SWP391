/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProgramingLanguage;
import model.Request;
import model.Skill;
import model.Status;

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
            xtm.setNull(6, java.sql.Types.INTEGER);
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

    public List<Request> getRequestByID(int userId) {
        try {
            List<Request> list = new ArrayList<>();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + "where userId = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                Status status = new Status();
                String xSQL = "Select * from Statuses where statusId = ?";
                PreparedStatement qtm = connection.prepareStatement(xSQL);
                qtm.setInt(1, rs.getInt(6));
                ResultSet resultSet = qtm.executeQuery();
                while (resultSet.next()) {
                    status.setId(resultSet.getInt(1));
                    status.setName(resultSet.getString(2));
                }
                request.setStatus(status);
                String qSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement xtm = connection.prepareStatement(qSQL);
                xtm.setInt(1, rs.getInt(1));
                ResultSet a = xtm.executeQuery();
                ProgramingLanguage pg = new ProgramingLanguage();
                ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
                if (a.next()) {
                    pg = programingLanguageDAO.getProgramingLanguageById(a.getInt(4));
                }
                request.setPro(pg);
                List<Skill> skills = new ArrayList<Skill>();
                String mSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement b = connection.prepareStatement(mSQL);
                xtm.setInt(1, rs.getInt(1));
                ResultSet ab = xtm.executeQuery();
                while (ab.next()) {
                    SkillDAO skillDAO = new SkillDAO();
                    skills.add(skillDAO.getSkillById(ab.getInt(3)));
                }
                request.setSkills(skills);
                list.add(request);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateRequest(int userID, String id, String title, Date createdDate, Date deadline, String status, String pro, String[] skills, String content) {
        try {
            String xSQL = "UPDATE [dbo].[RequestDetail]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[requestContent] = ?\n"
                    + "      ,[createdDate] = ?\n"
                    + "      ,[deadline] = ?\n"
                    + "      ,[statusId] = ?\n"
                    + " WHERE requestId = ?";
            PreparedStatement xtm = connection.prepareStatement(xSQL);
            xtm.setInt(6, Integer.parseInt(id));
            xtm.setString(1, title);
            xtm.setString(2, content.trim());
            xtm.setDate(3, createdDate);
            xtm.setDate(4, deadline);
            xtm.setInt(5, Integer.parseInt(status));
            xtm.executeUpdate();
            String tSQL = "DELETE FROM [dbo].[requestSkillsChoices]\n"
                    + "      WHERE requestId = ?";
            PreparedStatement ptm = connection.prepareStatement(tSQL);
            ptm.setInt(1, Integer.parseInt(id));
            ptm.executeUpdate();
            for (String i : skills) {
                String qSQL = "INSERT INTO [dbo].[requestSkillsChoices]\n"
                        + "           ([requestId]\n"
                        + "           ,[skillId]\n"
                        + "           ,[languageId])\n"
                        + "     VALUES\n"
                        + "           (?,?,?)";
                PreparedStatement qtm = connection.prepareStatement(qSQL);
                qtm.setInt(1, Integer.parseInt(id));
                qtm.setInt(2, Integer.parseInt(i));
                qtm.setInt(3, Integer.parseInt(pro));
                qtm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public ArrayList<Request> getRequests() {
//        ArrayList<Request> skills = new ArrayList<>();
//        try {
//            String sql = "  select rd.requestId, u.username, rd.title, rd.statusId from RequestDetail rd \n"
//                    + "  inner join Requests r on r.requestId = rd.requestId inner join Users u on u.userId = r.userId inner join Statuses s on s.statusId = rd.statusId";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                skills.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return skills;
//    }
    public List<Request> getRequests() {
        try {
            List<Request> list = new ArrayList<>();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId join Users u on u.userId = re.userId\n";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                request.setUserName(rs.getString("username"));
                int mentorId=rs.getInt("mentorId");
                if(rs.wasNull()){
                    request.setMentorId(0);
                }else request.setMentorId(mentorId);
                Status status = new Status();
                String xSQL = "Select * from Statuses where statusId = ?";
                PreparedStatement qtm = connection.prepareStatement(xSQL);
                qtm.setInt(1, rs.getInt(6));
                ResultSet resultSet = qtm.executeQuery();
                while (resultSet.next()) {
                    status.setId(resultSet.getInt(1));
                    status.setName(resultSet.getString(2));
                }
                request.setStatus(status);
                String qSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement xtm = connection.prepareStatement(qSQL);
                xtm.setInt(1, rs.getInt(1));
                ResultSet a = xtm.executeQuery();
                ProgramingLanguage pg = new ProgramingLanguage();
                ProgramingLanguageDAO programingLanguageDAO = new ProgramingLanguageDAO();
                if (a.next()) {
                    pg = programingLanguageDAO.getProgramingLanguageById(a.getInt(4));
                }
                request.setPro(pg);
                List<Skill> skills = new ArrayList<Skill>();
                String mSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement b = connection.prepareStatement(mSQL);
                xtm.setInt(1, rs.getInt(1));
                ResultSet ab = xtm.executeQuery();
                while (ab.next()) {
                    SkillDAO skillDAO = new SkillDAO();
                    skills.add(skillDAO.getSkillById(ab.getInt(3)));
                }
                request.setSkills(skills);
                list.add(request);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    public ArrayList<Request> searchRequests(String search) {
//        ArrayList<Request> skills = new ArrayList<>();
//        try {
//            String sql = "  select rd.requestId, u.username, rd.title, rd.statusId from RequestDetail rd \n"
//                    + "  inner join Requests r on r.requestId = rd.requestId inner join Users u on u.userId = r.userId inner join Statuses s on s.statusId = rd.statusId\n"
//                    + "  where rd.title like '%" + search + "%' or u.username like '%" + search + "%'";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                skills.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return skills;
//    }
}
