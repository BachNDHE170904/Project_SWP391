package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserDetails;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class UserDAO extends BaseDAO<User> {

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users s\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("name"));
                s.setPass(rs.getString("pass"));
                s.setUserId(rs.getInt("userId"));
                s.setIsAuthorized(false);
                users.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.username = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void insertUser(User us) {
        try {
            String sql ="insert into Users(username,password,userAuthorization) values(?,?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, us.getUsername());
            statement.setString(2, us.getPass());
            statement.setBoolean(3, us.isIsAuthorized());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertUserDetails(UserDetails us) {
        try {
            String sql = "insert into UserDetail(userId,email,username,phone,fullname,dob,gender,userAddress,roleId) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, us.getUserId());
            statement.setString(2, us.getEmail());
            statement.setString(3, us.getUsername());
            statement.setString(4, us.getPhone());
            statement.setString(5, us.getFullname());
            statement.setDate(6, us.getDob());
            statement.setBoolean(7, us.isSex());
            statement.setString(8, us.getAddress());
            statement.setInt(9, us.getRoleId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void main(String[] args) {
//        UserDAO db=new UserDAO();
//         Date date=Date.valueOf("2010-10-10");
//        UserDetails ud=new UserDetails("1", "1",  "1",  "1", date ,  true, 4,  "a",  "a",1,false);
//        db.insertUser(ud);
//    }
    public void change(User u){
        String sql = "update Users set password=? where username=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getPass());
            stm.setString(2, u.getUsername());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public User check() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean isEmailAssociated(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // If count > 0, the email is associated with at least one user.
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // If there was an error or the email is not associated with any user.
    }
}

