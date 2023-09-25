package DAO;

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
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
                s.setEmail("email");
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

        }  catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }
    public UserDetails getUserDetails(String username) {
        try {
            String sql = "SELECT * FROM UserDetail,Users \n"
                    + "WHERE UserDetail.userId=Users.userId and Users.username=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
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

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }
    public void insertUser(User us) {
        try {
            String sql ="insert into Users(email,username,password,userAuthorization) values(?,?,?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, us.getEmail());
            statement.setString(2, us.getUsername());
            statement.setString(3, us.getPass());
            statement.setBoolean(4, us.isIsAuthorized());
            statement.executeUpdate();
        }  catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
        }  catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    //update User Info
    public int updateUserInfo(UserDetails us) throws SQLException {
    String sql = "UPDATE UserDetail SET dob = ?, gender = ?, userAddress = ?, phone = ? WHERE id = ?";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setDate(1, us.getDob());
    pstmt.setBoolean(2, us.isSex());
    pstmt.setString(3, us.getAddress());
    pstmt.setString(4, us.getPhone());
    int rows = pstmt.executeUpdate();
    pstmt.close();
    return rows;
  }

    public int updateUser(String username, String fullname, String dob, String gender, String address, String phone, String currentPassword, String newPassword) throws SQLException{
        // Tạo câu lệnh SQL update
        String sql = "UPDATE Users u INNER JOIN UserDetails ud ON u.userId = ud.userId " + 
                     "SET u.username = ?, ud.fullname = ?, ud.dob = ?, ud.gender = ?, ud.address = ?, ud.phone = ?, u.password = ? ";
        
                      
        // Thực thi câu lệnh SQL, truyền tham số
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username); 
        pstmt.setString(2, fullname);
        pstmt.setString(3, dob);
        pstmt.setString(4, gender);
        pstmt.setString(5, address);
        pstmt.setString(6, phone);
        pstmt.setString(7, newPassword);
        
        // Thực thi câu lệnh UPDATE 
        int rows = pstmt.executeUpdate();
  
        // Đóng kết nối
        pstmt.close();
        return rows;
  
  
    }

}
