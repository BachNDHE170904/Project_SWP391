/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Status;

/**
 *
 * @author ddtd2
 */
public class StatusDAO extends BaseDAO<Status>{
    public ArrayList<Status> getAll() {
        ArrayList<Status> skills = new ArrayList<>();
        try {
            String sql = "Select * from Statuses";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Status s = new Status();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                skills.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skills;
    }
}
