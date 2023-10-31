package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mentor;
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
            String sql = "SELECT * FROM Users s,UserStatus us where s.userId=us.userId\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setIsAuthorized(rs.getBoolean("userAuthorization"));
                s.setStatus(rs.getString("userStatus"));
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

    public User getUserByID(int userId) {
        try {
            String sql = "SELECT * FROM UserStatus us, Users s WHERE us.userId = s.userId AND us.userId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setPass(rs.getString("password"));
                s.setUserId(rs.getInt("userId"));
                s.setEmail(rs.getString("email"));
                s.setStatus(rs.getString("userStatus"));
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
    public String getUserStatus(int userID) {
        try {
            String sql = "SELECT * FROM UserStatus s\n"
                    + "WHERE s.userId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("userStatus");
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

    public void insertUserStatus(int userId,String status) {
        try {
            String sql = "insert into UserStatus(userId,userStatus) values(?,?)\n;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status);
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

    public boolean updateUserRoleToMentee(int userId) {
        String sql = "update UserDetail set roleId = ? where userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, 3);
            stm.setInt(2, userId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    public boolean updateMenteeRoleToMentor(int userId) {
        String sql = "update UserDetail set roleId = ? where userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, 4);
            stm.setInt(2, userId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public String getEncryptedPassword(String email) throws SQLException {

        String sql = "SELECT password FROM users WHERE email = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);

        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return rs.getString("password");
        }

        return null;
    }

    /**
     * Check Role of user is input role
     *
     * @param userId
     * @param roleId
     * @return true if role in db is the same as input role
     */
    public boolean checkRole(int userId, int roleId) {
        String sql = "select roleId from UserDetail where userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            int role = 0;
            if (rs.next()) {
                role = rs.getInt("roleId");
            }
            return role == roleId;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public void updateUserDetail(int userID, String username, String fullname, String phone, String address, boolean sex, Date dob, String avatar) {
        String sql = "UPDATE [dbo].[UserDetail]\n"
                + "   SET \n"
                + "       [username] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[fullname] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[userAddress] = ?\n"
                + " WHERE [userId] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, phone);
            stm.setString(3, fullname);
            stm.setDate(4, dob);
            stm.setBoolean(5, sex);
            stm.setString(6, address);
            stm.setInt(7, userID);
            stm.executeUpdate();
            String xSQL = "UPDATE [dbo].[UserAvatar]\n"
                    + "   SET [avatarLink] = ?\n"
                    + " WHERE userId = ?";
            PreparedStatement qtm = connection.prepareStatement(xSQL);
            qtm.setString(1, avatar);
            qtm.setInt(2, userID);
            qtm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public ArrayList<UserDetails> getAllUsers() {
        ArrayList<UserDetails> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users, UserDetail, UserStatus where Users.userId = UserDetail.userId  AND Users.userId= UserStatus.userId AND (UserDetail.roleId = 2 OR UserDetail.roleId = 3)";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserDetails s = new UserDetails();
                s.setUserId(rs.getInt("userId"));
                s.setFullname(rs.getString("fullname"));
                s.setUsername(rs.getString("username"));
                s.setRoleId(rs.getInt("roleId"));
                s.setStatus(rs.getString("userStatus"));
                users.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNumberOfRequests(int userId) {
        String sql = "SELECT COUNT(r.requestId) AS 'Number requests of Mentee' FROM RequestDetail rd JOIN Requests r ON rd.requestId = r.requestId \n" +
                     "JOIN Users u ON u.userId = r.userId AND u.userId = ? where rd.statusId = 1 OR rd.statusId = 2";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("Number requests of Mentee");
                rs.close();
                stm.close();
                return count;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public boolean updateUserStatus(int userId, String status) {
        String sql = "UPDATE UserStatus set userStatus = ? where userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            if(status.equalsIgnoreCase("Active"))
            stm.setString(1, "inactive");
            else stm.setString(1, "active");
            stm.setInt(2, userId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
