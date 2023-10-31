package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;
import model.Mentor;
import model.Skill;

public class MentorDAO extends BaseDAO<Skill> {

    public Mentor getMentorByUserID(int userID) {
        Mentor mentor = new Mentor();
        try {
            String selectMentor = "select * from Mentor m join MentorCV mcv on m.mentorId = mcv.mentorId where m.userId = ?\n";
            String selectSkills = "select * from MentorSkills m where m.mentorId = ?\n";
            String selectLanguages = "select * from MentorProgramingLanguage m where m.mentorId = ?\n";

            PreparedStatement selectMentorStatement = connection.prepareStatement(selectMentor);
            selectMentorStatement.setInt(1, userID);
            ResultSet rs1 = selectMentorStatement.executeQuery();
            while (rs1.next()) {
                mentor.setMentorId(rs1.getInt("mentorId"));
                mentor.setUserid(userID);
                mentor.setProfession(rs1.getString("profession"));
                mentor.setProfessionInfo(rs1.getString("professionIntro"));
                mentor.setServiceInfo(rs1.getString("serviceIntro"));
                mentor.setAchivementInfo(rs1.getString("achivementIntro"));

                ArrayList<Integer> skills = new ArrayList<>();
                ArrayList<Integer> languages = new ArrayList<>();
                PreparedStatement selectSkillsStatement = connection.prepareStatement(selectSkills);
                selectSkillsStatement.setInt(1, mentor.getMentorId());
                ResultSet rs2 = selectSkillsStatement.executeQuery();
                while (rs2.next()) {
                    skills.add(rs2.getInt("skillId"));
                }
                PreparedStatement selectLanguagesStatement = connection.prepareStatement(selectLanguages);
                selectLanguagesStatement.setInt(1, mentor.getMentorId());
                ResultSet rs3 = selectLanguagesStatement.executeQuery();
                while (rs3.next()) {
                    languages.add(rs3.getInt("languageId"));
                }
                mentor.setSkillsId(skills);
                mentor.setLanguageId(languages);
            }
            return mentor;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramingLanguageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Mentor getMentorByMentorID(int mentorId) {
        Mentor mentor = new Mentor();
        try {
            String selectMentor = "select * from Mentor m join MentorCV mcv on m.mentorId = mcv.mentorId where m.mentorId = ?\n";
            String selectSkills = "select * from MentorSkills m where m.mentorId = ?\n";
            String selectLanguages = "select * from MentorProgramingLanguage m where m.mentorId = ?\n";

            PreparedStatement selectMentorStatement = connection.prepareStatement(selectMentor);
            selectMentorStatement.setInt(1, mentorId);
            ResultSet rs1 = selectMentorStatement.executeQuery();
            while (rs1.next()) {
                mentor.setMentorId(mentorId);
                mentor.setUserid(rs1.getInt("userId"));
                mentor.setProfession(rs1.getString("profession"));
                mentor.setProfessionInfo(rs1.getString("professionIntro"));
                mentor.setServiceInfo(rs1.getString("serviceIntro"));
                mentor.setAchivementInfo(rs1.getString("achivementIntro"));

                ArrayList<Integer> skills = new ArrayList<>();
                ArrayList<Integer> languages = new ArrayList<>();
                PreparedStatement selectSkillsStatement = connection.prepareStatement(selectSkills);
                selectSkillsStatement.setInt(1, mentor.getMentorId());
                ResultSet rs2 = selectSkillsStatement.executeQuery();
                while (rs2.next()) {
                    skills.add(rs2.getInt("skillId"));
                }
                PreparedStatement selectLanguagesStatement = connection.prepareStatement(selectLanguages);
                selectLanguagesStatement.setInt(1, mentor.getMentorId());
                ResultSet rs3 = selectLanguagesStatement.executeQuery();
                while (rs3.next()) {
                    languages.add(rs3.getInt("languageId"));
                }
                mentor.setSkillsId(skills);
                mentor.setLanguageId(languages);
            }
            return mentor;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramingLanguageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insertMentor(Mentor mentor) throws SQLException {
        connection.setAutoCommit(false);
        String insertMentor = "insert into Mentor(userId) values(?)\n;";
        String insertMentorCV = "insert into MentorCV(mentorId, profession, professionIntro, serviceIntro, achivementIntro) values(?,?,?,?,?)\n;";
        String insertMentorSkills = "insert into MentorSkills(mentorId, skillId) values(?,?)\n;";
        String insertMentorLanguages = "insert into MentorProgramingLanguage(mentorId, languageId) values(?,?)\n;";
        int mentorId = 0;
        try ( PreparedStatement insertMentorStatement = connection.prepareStatement(insertMentor, Statement.RETURN_GENERATED_KEYS);  PreparedStatement insertMentorCVStatement = connection.prepareStatement(insertMentorCV);  PreparedStatement insertMentorSkillsStatement = connection.prepareStatement(insertMentorSkills);  PreparedStatement insertMentorLanguagesStatement = connection.prepareStatement(insertMentorLanguages);) {
            insertMentorStatement.setInt(1, mentor.getUserid());
            insertMentorStatement.executeUpdate();
            ResultSet rs = insertMentorStatement.getGeneratedKeys();
            if (rs.next()) {
                mentorId = rs.getInt(1);
                int i = 1;
                insertMentorCVStatement.setInt(i++, mentorId);
                insertMentorCVStatement.setString(i++, mentor.getProfession());
                insertMentorCVStatement.setString(i++, mentor.getProfessionInfo());
                insertMentorCVStatement.setString(i++, mentor.getServiceInfo());
                insertMentorCVStatement.setString(i++, mentor.getAchivementInfo());
                insertMentorCVStatement.executeUpdate();

                for (int sillId : mentor.getSkillsId()) {
                    i = 1;
                    insertMentorSkillsStatement.setInt(i++, mentorId);
                    insertMentorSkillsStatement.setInt(i++, sillId);
                    insertMentorSkillsStatement.executeUpdate();
                }

                for (int languageId : mentor.getLanguageId()) {
                    i = 1;
                    insertMentorLanguagesStatement.setInt(i++, mentorId);
                    insertMentorLanguagesStatement.setInt(i++, languageId);
                    insertMentorLanguagesStatement.executeUpdate();
                }
            }
            connection.commit();
            return mentorId;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
            connection.rollback();
            return 0;
        } finally {
            connection.close();
        }
    }

    public boolean updateMentor(Mentor mentor) throws SQLException {
        connection.setAutoCommit(false);
        String updateMentorCV = "UPDATE MentorCV SET profession = ?, professionIntro = ?, serviceIntro = ?, achivementIntro = ? WHERE mentorId = ?\n;";

        String insertMentorSkills = "insert into MentorSkills(mentorId, skillId) values(?,?)\n;";
        String deleteMentorSkills = "DELETE FROM MentorSkills WHERE mentorId = ? and skillId in (?)\n;";

        String insertMentorLanguages = "insert into MentorProgramingLanguage(mentorId, languageId) values(?,?)\n;";
        String deleteMentorLanguages = "DELETE FROM MentorProgramingLanguage WHERE mentorId = ? and languageId in (?)\n;";

        try ( PreparedStatement updateMentorCVStatement = connection.prepareStatement(updateMentorCV);  PreparedStatement insertMentorSkillsStatement = connection.prepareStatement(insertMentorSkills);  PreparedStatement deleteMentorSkillsStatement = connection.prepareStatement(deleteMentorSkills);  PreparedStatement insertMentorLanguagesStatement = connection.prepareStatement(insertMentorLanguages);  PreparedStatement deleteMentorLanguagesStatement = connection.prepareStatement(deleteMentorLanguages);) {

            int i = 1;
            updateMentorCVStatement.setString(i++, mentor.getProfession());
            updateMentorCVStatement.setString(i++, mentor.getProfessionInfo());
            updateMentorCVStatement.setString(i++, mentor.getServiceInfo());
            updateMentorCVStatement.setString(i++, mentor.getAchivementInfo());
            updateMentorCVStatement.setInt(i++, mentor.getMentorId());
            updateMentorCVStatement.executeUpdate();

            ArrayList<Integer> skillsDB = getSkillIdByMentorId(mentor.getMentorId());
            ArrayList<Integer> languagesDB = getLanguageIdByMentorId(mentor.getMentorId());

            ArrayList<Integer> skills = mentor.getSkillsId();
            ArrayList<Integer> languages = mentor.getLanguageId();

            for (int skillId : skills) {
                if (skillsDB.contains(skillId)) {
                    skillsDB.remove(new Integer(skillId));
                } else {
                    i = 1;
                    insertMentorSkillsStatement.setInt(i++, mentor.getMentorId());
                    insertMentorSkillsStatement.setInt(i++, skillId);
                    insertMentorSkillsStatement.executeUpdate();
                    skillsDB.remove(new Integer(skillId));
                }
            }
            if (!skillsDB.isEmpty()) {
                StringJoiner joiner = new StringJoiner(",");
                for (Integer skillId : skillsDB) {
                    joiner.add(skillId.toString());
                }
                i = 1;
                deleteMentorSkillsStatement.setInt(i++, mentor.getMentorId());
                deleteMentorSkillsStatement.setString(i++, joiner.toString());
                deleteMentorSkillsStatement.executeUpdate();
            }

            for (int languageId : languages) {
                if (languagesDB.contains(languageId)) {
                    languagesDB.remove(new Integer(languageId));
                } else {
                    i = 1;
                    insertMentorLanguagesStatement.setInt(i++, mentor.getMentorId());
                    insertMentorLanguagesStatement.setInt(i++, languageId);
                    insertMentorLanguagesStatement.executeUpdate();
                    languagesDB.remove(new Integer(languageId));
                }
            }
            if (!languagesDB.isEmpty()) {
                StringJoiner joiner = new StringJoiner(",");
                for (Integer languageId : languagesDB) {
                    joiner.add(languageId.toString());
                }
                i = 1;
                deleteMentorLanguagesStatement.setInt(i++, mentor.getMentorId());
                deleteMentorLanguagesStatement.setString(i++, joiner.toString());
                deleteMentorLanguagesStatement.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
    }

    public boolean userHadCV(int userId) {
        try {
            String sql = "select count(mentorId) from Mentor where userId = ?\n";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            boolean flag = false;
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    flag = true;
                }
            }
            return flag;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Integer> getLanguageIdByMentorId(int mentorId) {
        try {
            String sql = "SELECT languageId FROM MentorProgramingLanguage where mentorId=? \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mentorId);
            ResultSet rs = statement.executeQuery();

            ArrayList<Integer> languages = new ArrayList<>();
            while (rs.next()) {
                languages.add(rs.getInt(1));
            }
            return languages;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public ArrayList<Integer> getSkillIdByMentorId(int mentorId) {
        try {
            String sql = "SELECT skillId FROM MentorSkills where mentorId=? \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mentorId);
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> skills = new ArrayList<>();
            while (rs.next()) {
                skills.add(rs.getInt(1));
            }
            return skills;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public ArrayList<Mentor> getAllMentors() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "ud.userId AS ID,\n"
                    + "m.mentorId,\n"
                    + "ud.fullname AS Fullname,\n"
                    + "ud.username AS AccountName,\n"
                    + "mc.profession AS Profession,\n"
                    + "us.userStatus AS userStatus,"
                    + "u.userAuthorization AS UserAuthorized\n"
                    + "FROM UserDetail ud \n"
                    + "INNER JOIN UserStatus us ON us.userId = ud.userId\n"
                    + "INNER JOIN Users u ON u.userId = ud.userId\n"
                    + "INNER JOIN Mentor m ON ud.userId = m.userId\n"
                    + "INNER JOIN MentorCV mc ON m.mentorId = mc.mentorId\n"
                    + "where ud.roleId = 4";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setUserid(rs.getInt("ID"));
                mentor.setMentorId(rs.getInt("mentorId"));
                mentor.setFullname(rs.getString("Fullname"));
                mentor.setUsername(rs.getString("AccountName"));
                // Lấy thông tin về profession từ ResultSet và thiết lập cho mentor
                mentor.setProfession(rs.getString("Profession"));
                mentor.setStatus(rs.getString("userStatus"));
                mentor.setAverageRating(this.getAverageRatingOfMentorByMentorId(mentor.getMentorId()));
                mentors.add(mentor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mentors;
    }

    public int getTotalRatingOfMentorByMentorId(int mentorId) {
        try {
            String sql = "select Count(rt.rating)as totalRating from Requests r Inner Join RequestDetail rd on r.requestId=rd.requestId\n"
                    + "Inner Join Rating rt on r.requestId=rt.requestId\n"
                    + "where rd.mentorId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mentorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("totalRating");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double getAverageRatingOfMentorByMentorId(int mentorId) {
        try {
            String sql = "select AVG(rt.rating)as totalRating from Requests r Inner Join RequestDetail rd on r.requestId=rd.requestId\n"
                    + "Inner Join Rating rt on r.requestId=rt.requestId\n"
                    + "where rd.mentorId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mentorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("totalRating");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Comment> getCommentsOfMentorByMentorId(int mentorId) {
        try {
            ArrayList<Comment> comments = new ArrayList<>();
            String sql = "select c.commentId,c.commentDetail,r.userId,rd.mentorId,c.createdDate from Requests r Inner Join RequestDetail rd on r.requestId=rd.requestId\n"
                    + "Inner Join Rating rt on r.requestId=rt.requestId\n"
                    + "INNER JOIN Comment c on rt.commentId=c.commentId\n"
                    + "where rd.mentorId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mentorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Comment c=new Comment();
                c.setCommentId(rs.getInt(1));
                c.setCommentDetail(rs.getString(2));
                c.setUserId(rs.getInt(3));
                c.setMentorId(rs.getInt(4));
                c.setCreatedDate(rs.getDate(5));
                comments.add(c);
            }
            return comments;
        } catch (SQLException ex) {
            Logger.getLogger(MentorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
