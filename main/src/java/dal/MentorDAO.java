package dal;

import model.Skill;

public class MentorDAO extends BaseDAO<Skill> {
//    public int insertMentor(Mentor mentor) throws SQLException {
//        try {
//            connection.setAutoCommit(false);
//            String insertMentor = "insert into Mentor(userId) values(?)\n;";
//            String insertMentorCV = "insert into Mentor(userId) values(?)\n;";
//            String insertMentorSkills = "insert into Mentor(userId) values(?)\n;";
//
//            try ( PreparedStatement insertMentorStatement = connection.prepareStatement(insertMentor, Statement.RETURN_GENERATED_KEYS);  PreparedStatement insertMentorCVStatement = connection.prepareStatement(insertMentorCV, Statement.RETURN_GENERATED_KEYS);  PreparedStatement insertMentorSkillsStatement = connection.prepareStatement(insertMentorSkills, Statement.RETURN_GENERATED_KEYS);) {
//                connection.commit();
//                insertMentorStatement.setInt(1, mentor.getUserid());
//                insertMentorStatement.executeUpdate();
//                ResultSet rs = insertMentorStatement.getGeneratedKeys();
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//                insertMentorStatement.executeUpdate();
//
//                    }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            connection.rollback();
//        } finally {
//            connection.close();
//        }
//    }
//
//    public void updateCoffeeSales(HashMap<String, Integer> salesForWeek) throws SQLException {
//        String updateString
//                = "update COFFEES set SALES = ? where COF_NAME = ?";
//        String updateStatement
//                = "update COFFEES set TOTAL = TOTAL + ? where COF_NAME = ?";
//
//        try ( PreparedStatement updateSales = con.prepareStatement(updateString);  PreparedStatement updateTotal = con.prepareStatement(updateStatement)) {
//            con.setAutoCommit(false);
//            for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
//                updateSales.setInt(1, e.getValue().intValue());
//                updateSales.setString(2, e.getKey());
//                updateSales.executeUpdate();
//
//                updateTotal.setInt(1, e.getValue().intValue());
//                updateTotal.setString(2, e.getKey());
//                updateTotal.executeUpdate();
//                con.commit();
//            }
//        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
//            if (con != null) {
//                try {
//                    System.err.print("Transaction is being rolled back");
//                    con.rollback();
//                } catch (SQLException excep) {
//                    JDBCTutorialUtilities.printSQLException(excep);
//                }
//            }
//        }
//    }
}
