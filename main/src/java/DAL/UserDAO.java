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

    public User getUser(String email, String pass) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.email = ? and s.password = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserByUserName(String username) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.username=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserByEmailOnly(String email) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void change(String email, String newPassword) {
        String sql = "update Users set password=? where email=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String getUserAvatar(int userId) {
        try {
            String sql = "SELECT * FROM UserAvatar s\n"
                    + "WHERE s.userId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String avatarLink = rs.getString("avatarLink");
                return avatarLink;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public UserDetails getUserDetails(String email) {
        try {
            String sql = "SELECT * FROM UserDetail,Users \n"
                    + "WHERE UserDetail.userId=Users.userId and Users.email=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                UserDetails s = new UserDetails();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                s.setAddress(rs.getString("userAddress"));
                s.setDob(rs.getDate("dob"));
                s.setPhone(rs.getString("phone"));
                s.setFullname(rs.getString("fullname"));
                s.setSex(rs.getBoolean("gender"));
                s.setRoleId(rs.getInt("roleId"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertUser(User us) {
        try {
            String sql = "insert into Users(email,username,password,userAuthorization) values(?,?,?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, us.getEmail());
            statement.setString(2, us.getUsername());
            statement.setString(3, us.getPass());
            statement.setBoolean(4, us.isIsAuthorized());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUserDetails(UserDetails us) {
        try {
            String sql = "insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId) values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, us.getUserId());
            statement.setString(2, us.getUsername());
            statement.setString(3, us.getPhone());
            statement.setString(4, us.getFullname());
            statement.setDate(5, us.getDob());
            statement.setBoolean(6, us.isSex());
            statement.setString(7, us.getAddress());
            statement.setInt(8, us.getRoleId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
    
    public boolean updatePassword(String email, String newPassword) {
        String sql = "update users set password = ? where email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, email);
            int rowCount = stm.executeUpdate();
            connection.close();
            return rowCount > 0;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean updateUserAuthorization(String email) {
        String sql = "update users set userAuthorization = ? where email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setString(2, email);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
