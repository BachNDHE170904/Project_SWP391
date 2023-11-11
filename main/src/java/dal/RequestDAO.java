/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.Common;
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

public class RequestDAO extends BaseDAO<Skill> {

    public void insertRequest(int userID, String title, String content, Date deadline, int statusID, String[] skills, int languageID, long price) {
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
                    + "           ,[mentorId]"
                    + "           ,[price])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,GETDATE(),?,?,?,?)";
            PreparedStatement xtm = connection.prepareStatement(xSQL);
            xtm.setInt(1, requestID);
            xtm.setString(2, title);
            xtm.setString(3, content);
            xtm.setDate(4, deadline);
            xtm.setInt(5, statusID);
            xtm.setNull(6, java.sql.Types.INTEGER);
            xtm.setLong(7, price);
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
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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

    public Request getRequestByRequestID(int requestId) {
        try {
            Request request = new Request();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + "where r.requestId = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, requestId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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
            }
            return request;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateRequest(int userID, String id, String title, Date createdDate, Date deadline, String status, String pro, String[] skills, String content, long price) {
        try {
            String xSQL = "UPDATE [dbo].[RequestDetail]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[requestContent] = ?\n"
                    + "      ,[createdDate] = ?\n"
                    + "      ,[deadline] = ?\n"
                    + "      ,[statusId] = ?\n"
                    + "      ,[price] = ?\n"
                    + " WHERE requestId = ?";
            PreparedStatement xtm = connection.prepareStatement(xSQL);
            xtm.setInt(7, Integer.parseInt(id));
            xtm.setString(1, title);
            xtm.setString(2, content.trim());
            xtm.setDate(3, createdDate);
            xtm.setDate(4, deadline);
            xtm.setInt(5, Integer.parseInt(status));
            xtm.setLong(6, price);
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
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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
    public boolean updateRequestStatusToClosed(int requestId) {
        try {
            String sql = "update RequestDetail set statusId = ? where requestId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 4);
            statement.setInt(2, requestId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setMentorIdForRequest(int requestId, int mentorId) {
        try {
            String sql = "update RequestDetail set mentorId = ? where requestId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            if (mentorId == 0) {
                statement.setNull(1, java.sql.Types.INTEGER);
            } else {
                statement.setInt(1, mentorId);
            }
            statement.setInt(2, requestId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        public boolean setPriceForRequest(int requestId, long price) {
        try {
            String sql = "update RequestDetail set price = ? where requestId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, price);
            statement.setInt(2, requestId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Request> getRequestByMentorID(int mentorId, int stt, int[] page) {
        try {
            ArrayList<Request> list = new ArrayList<>();
            String sql = "Select r.requestId, title, requestContent, deadline, statusId, m.userId as mentorId, username, u.userId as menteeId,r.price "
                    + "from RequestDetail r join Requests re on r.requestId = re.requestId "
                    + "join Users u on u.userId = re.userId "
                    + "join Mentor m on m.mentorId = r.mentorId "
                    + "where m.userId = ? and r.statusId = ? "
                    + "order by r.requestId offset ? rows fetch next ? rows only";
            PreparedStatement ptm = connection.prepareStatement(sql);
            int i = 1;
            ptm.setInt(i++, mentorId);
            ptm.setInt(i++, stt);
            ptm.setInt(i++, page[0]);
            ptm.setInt(i++, page[1]);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setDeadline(rs.getDate(4));
                request.setMentorId(rs.getInt(6));
                request.setUserName(rs.getString(7));
                request.setUserID(rs.getInt(8));
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
                Status status = new Status();
                String xSQL = "Select * from Statuses where statusId = ?";
                PreparedStatement qtm = connection.prepareStatement(xSQL);
                qtm.setInt(1, rs.getInt(5));
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
                List<Skill> skills = new ArrayList<>();
                String mSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement b = connection.prepareStatement(mSQL);
                b.setInt(1, rs.getInt(1));
                ResultSet ab = b.executeQuery();
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

    public ArrayList<Request> getRequestHistoryByMentorID(int mentorId, int page, int total) {
        try {
            ArrayList<Request> list = new ArrayList<>();
            String sql = "Select r.requestId, title, requestContent, deadline, statusId, m.userId as mentorId, username, u.userId as menteeId,r.price "
                    + "from RequestDetail r join Requests re on r.requestId = re.requestId "
                    + "join Users u on u.userId = re.userId "
                    + "join Mentor m on m.mentorId = r.mentorId "
                    + "where m.userId = ? and (r.statusId=3 or r.statusId=4)"
                    + "order by r.requestId offset ? rows fetch next ? rows only";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, mentorId);
            ptm.setInt(2, page);
            ptm.setInt(3, total);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setDeadline(rs.getDate(4));
                request.setMentorId(rs.getInt(6));
                request.setUserName(rs.getString(7));
                request.setUserID(rs.getInt(8));
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
                Status status = new Status();
                String xSQL = "Select * from Statuses where statusId = ?";
                PreparedStatement qtm = connection.prepareStatement(xSQL);
                qtm.setInt(1, rs.getInt(5));
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
                List<Skill> skills = new ArrayList<>();
                String mSQL = "Select * from requestSkillsChoices where requestId = ?";
                PreparedStatement b = connection.prepareStatement(mSQL);
                b.setInt(1, rs.getInt(1));
                ResultSet ab = b.executeQuery();
                while (ab.next()) {
                    SkillDAO skillDAO = new SkillDAO();
                    skills.add(skillDAO.getSkillById(ab.getInt(3)));
                }
                request.setSkills(skills);
                String ratingSQL = "select *from Rating r INNER JOIN Comment c on r.commentId=c.commentId\n"
                        + "INNER JOIN RequestDetail rd on rd.requestId=r.requestId \n"
                        + "where r.requestId=?";
                PreparedStatement c = connection.prepareStatement(ratingSQL);
                c.setInt(1, rs.getInt(1));
                ResultSet abc = c.executeQuery();
                while (abc.next()) {
                    request.setCommentDetail(abc.getString("commentDetail"));
                    request.setRating(abc.getInt("rating"));
                }
                list.add(request);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countRequestByMentorID(int mentorId, int status) {
        int count = 0;
        try {
            String sql = "Select count(r.requestId) "
                    + "from RequestDetail r join Requests re on r.requestId = re.requestId "
                    + "join Users u on u.userId = re.userId "
                    + "join Mentor m on m.mentorId = r.mentorId "
                    + "where m.userId = ? and r.statusId = ? ";
            PreparedStatement ptm = connection.prepareStatement(sql);
            int i = 1;
            ptm.setInt(i++, mentorId);
            ptm.setInt(i++, status);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int countRequestByMentorIDHistory(int mentorId) {
        int count = 0;
        try {
            String sql = "Select count(r.requestId) "
                    + "from RequestDetail r join Requests re on r.requestId = re.requestId "
                    + "join Users u on u.userId = re.userId "
                    + "join Mentor m on m.mentorId = r.mentorId "
                    + "where m.userId = ? and (r.statusId=3 or r.statusId=4)";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, mentorId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public boolean updateRequestStatus(int requestId, int status) {
        try {
            String sql = "update RequestDetail set statusId = ? where requestId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, requestId);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int countRequestByUserId(int userId) {
        try {
            String sql = "Select count(*) from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + " where userId = ?";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Request> getPagingRequestByID(int userId, int index) {
        try {
            List<Request> list = new ArrayList<>();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + "where userId = ? order by r.requestId offset ? rows fetch next 10 rows only";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userId);
            int n = (index - 1) * 10;
            ptm.setInt(2, n);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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

    public int countRequest() {
        try {
            String sql = "Select count(*) from RequestDetail r join Requests re on r.requestId = re.requestId\n";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countPagingRequests(String key, int statusId) {
        try {
            String sql = "Select Count(*) from RequestDetail r join Requests re on r.requestId = re.requestId join Users u on u.userId = re.userId Where username like ?\n";
            if (statusId > 0) {
                sql += "and statusId = ? ";
            }
            PreparedStatement ptm = connection.prepareStatement(sql);
            int i = 1;
            ptm.setString(i++, "%" + key + "%");
            if (statusId > 0) {
                ptm.setInt(i++, statusId);
            }
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Request> getPagingRequests(int index, String key, int statusId) {
        try {
            List<Request> list = new ArrayList<>();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId join Users u on u.userId = re.userId Where username like ?\n";
            if (statusId > 0) {
                sql += "and statusId = ? ";
            }
            sql += " order by r.requestId offset ? rows fetch next 10 rows only";
            PreparedStatement ptm = connection.prepareStatement(sql);
            int n = (index - 1) * 10;
            int i = 1;
            ptm.setString(i++, "%" + key + "%");
            if (statusId > 0) {
                ptm.setInt(i++, statusId);
            }
            ptm.setInt(i++, n);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                request.setUserName(rs.getString("username"));
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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

    public List<Request> getPagingRequestByIDWhichIsClosed(int userId, int index) {
        try {
            List<Request> list = new ArrayList<>();
            String sql = "Select * from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + "where userId = ? and r.statusId = 4 order by r.requestId offset ? rows fetch next 10 rows only";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userId);
            int n = (index - 1) * 10;
            ptm.setInt(2, n);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt(1));
                request.setTitle(rs.getString(2));
                request.setContent(rs.getString(3));
                request.setCreateDate(rs.getDate(4));
                request.setDeadline(rs.getDate(5));
                int mentorId = rs.getInt("mentorId");
                if (rs.wasNull()) {
                    request.setMentorId(0);
                } else {
                    request.setMentorId(mentorId);
                }
                long price = rs.getLong("price");
                if (rs.wasNull()) {
                    request.setMenteePrice(0);
                } else {
                    request.setMenteePrice(price);
                }
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

    public int countRequestByUserIdWhichIsClosed(int userId) {
        try {
            String sql = "Select count(*) from RequestDetail r join Requests re on r.requestId = re.requestId\n"
                    + " where userId = ? and r.statusId = 4";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, userId);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean insertProposalToRequest(int id, long price, int mentorId) {
        try {
            String insertComment = "insert into MentorSuggestions(requestId,price,mentorId) values(?,?,?)";
            PreparedStatement stm = connection.prepareStatement(insertComment);
            stm.setInt(1, id);
            stm.setLong(2, price);
            stm.setInt(3, mentorId);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProposalForRequest(int id, long price, int mentorId) {
        try {
            String insertComment = "update MentorSuggestions set price=? where requestId=? and mentorId=?";
            PreparedStatement stm = connection.prepareStatement(insertComment);
            stm.setLong(1, price);
            stm.setInt(2, id);
            stm.setInt(3, mentorId);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getProposalPriceForRequest(int id, int mentorId) {
        try {
            String insertComment = "select * from MentorSuggestions where requestId=? and mentorId=?";
            PreparedStatement stm = connection.prepareStatement(insertComment);
            stm.setInt(1, id);
            stm.setInt(2, mentorId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getLong("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long getPriceofRequest(int requestId) {
        try {
            String insertComment = "select * from RequestDetail where requestId=?";
            PreparedStatement stm = connection.prepareStatement(insertComment);
            stm.setInt(1, requestId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getLong("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
