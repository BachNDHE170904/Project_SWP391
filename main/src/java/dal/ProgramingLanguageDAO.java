/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.ProgramingLanguage;
import dal.BaseDAO;
import dal.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramingLanguageDAO extends BaseDAO<ProgramingLanguage> {

    public ArrayList<ProgramingLanguage> getProgramingLanguage() {
        ArrayList<ProgramingLanguage> programingLanguages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ProgrammingLanguage, LanguageStatus where ProgrammingLanguage.languageStatusId= LanguageStatus.languageStatusId \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProgramingLanguage s = new ProgramingLanguage();
                s.setLanguageId(rs.getInt("languageId"));
                s.setLanguageName(rs.getString("languageName"));
                s.setLanguageStatus(rs.getString("languageStatus"));
                programingLanguages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return programingLanguages;
    }

    public ProgramingLanguage getProgramingLanguageById(int programingLanguageId) {
        ProgramingLanguage s = new ProgramingLanguage();
        try {
            String sql = "SELECT * FROM ProgrammingLanguage,LanguageStatus where ProgrammingLanguage.languageStatusId=LanguageStatus.languageStatusId and ProgrammingLanguage.languageId=? \n";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, programingLanguageId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                s.setLanguageId(rs.getInt("languageId"));
                s.setLanguageName(rs.getString("languageName"));
                s.setLanguageStatus(rs.getString("languageStatus"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertProgramingLanguage(ProgramingLanguage pro) {
        try {
            String sql = "insert into ProgrammingLanguage(languageName,languageStatusId) values(?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int languageStatusId;
            statement.setString(1, pro.getLanguageName());
            if (pro.getLanguageStatus().equalsIgnoreCase("Active")) {
                languageStatusId = 1;
            } else {
                languageStatusId = 0;
            }
            statement.setInt(2, languageStatusId);
            statement.executeUpdate();
            statement.getGeneratedKeys();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateProgramingLanguageStatus(int languageId, String status) {
        String sql = "update ProgrammingLanguage set languageStatusId = ? where languageId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            if (status.equalsIgnoreCase("Active")) {
                stm.setInt(1, 1);
            } else {
                stm.setInt(1, 0);
            }
            stm.setInt(2, languageId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
