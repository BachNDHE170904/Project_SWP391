package DAO;

<<<<<<< Updated upstream
=======
import DAO.BaseDAO;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
=======
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> Stashed changes
        }
        return users;
    }

<<<<<<< Updated upstream
    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.username = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
=======
    public User getUser(String email, String pass) {
        try {
            String sql = "SELECT * FROM Users s\n"
                    + "WHERE s.email = ? and s.password = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pass);
>>>>>>> Stashed changes
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
<<<<<<< Updated upstream
                s.setEmail("email");
=======
                s.setEmail(rs.getString("email"));
>>>>>>> Stashed changes
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                return s;
            }

<<<<<<< Updated upstream
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
=======
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> Stashed changes
        }
        return null;
    }

<<<<<<< Updated upstream
    public UserDetails getUserDetails(String username) {
        try {
            String sql = "SELECT * FROM UserDetail,Users \n"
                    + "WHERE UserDetail.userId=Users.userId and Users.username=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
=======
    public void change(User u) {
        String sql = "update Users set password=? where email=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getPass());
            stm.setString(2, u.getEmail());
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                return s;
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
=======
                s.setAvt(rs.getString("avt"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
=======
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
<<<<<<<< Updated upstream:main/src/java/DAO/UserDAO.java

    public void updateUser(String username, String fullname, String dob, String gender, String address, String phone, String newPassword) throws SQLException {
        // Tạo câu lệnh SQL update
        String sqlUpdateGeneral = "update dbo.UserDetail set fullname = ? where dbo.UserDetail.username = ?";
=======
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(String username, String fullname, String dob, String gender, String address, String phone, String newPassword, String currentPassword, boolean changeFullname, String proimage) throws Exception {
        // Tạo câu lệnh SQL update
        String editImage = "";
        String sqlUpdateGeneral = "update dbo.UserDetail set fullname = ?  where dbo.UserDetail.username = ?";
>>>>>>> Stashed changes
        String sqlUpdateInfo = "update dbo.UserDetail set phone = ?, userAddress = ?, dob = ?, gender = ? where dbo.UserDetail.username = ?";
        String sqlUpdatePassword = "update dbo.[Users] set [password] = ? where username = ?";

        // Thực thi câu lệnh SQL, truyền tham số
<<<<<<< Updated upstream
        if (fullname != null && newPassword == null && dob == null && gender == null && address == null && phone == null) {
=======
        if (changeFullname) {
            System.out.println("changeFullname");
            if (!proimage.isEmpty()) {
                sqlUpdateGeneral = "update dbo.UserDetail set fullname = ?, avt = '" + proimage + "'  where dbo.UserDetail.username = ?";
            }
>>>>>>> Stashed changes
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdateGeneral);
            pstmt.setString(1, fullname);
            pstmt.setString(2, username);

            pstmt.executeUpdate();

            // Đóng kết nối
            pstmt.close();
        } else {
            System.out.println("Updateeeeeeeeeee");
<<<<<<< Updated upstream
            System.out.println(newPassword);
            
            PreparedStatement pstmt1 = connection.prepareStatement(sqlUpdatePassword);
            PreparedStatement pstmt2 = connection.prepareStatement(sqlUpdateInfo);
            
            pstmt1.setString(1, newPassword);
=======
            System.out.println(newPassword + " - " + newPassword.isEmpty());

            PreparedStatement pstmt1 = connection.prepareStatement(sqlUpdatePassword);
            PreparedStatement pstmt2 = connection.prepareStatement(sqlUpdateInfo);
            if (newPassword.isEmpty()) {
                pstmt1.setString(1, currentPassword);
            } else {
                pstmt1.setString(1, newPassword);
            }
>>>>>>> Stashed changes
            pstmt1.setString(2, username);

            pstmt2.setString(1, phone);
            pstmt2.setString(2, address);
            pstmt2.setString(3, dob);
            pstmt2.setString(4, gender);
            pstmt2.setString(5, username);
<<<<<<< Updated upstream
            
            
=======

>>>>>>> Stashed changes
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            // Đóng kết nối
            pstmt1.close();
            pstmt2.close();
        }
    }

<<<<<<< Updated upstream
========
    
    
>>>>>>>> Stashed changes:main/src/java/DAL/UserDAO.java
=======
>>>>>>> Stashed changes
}
