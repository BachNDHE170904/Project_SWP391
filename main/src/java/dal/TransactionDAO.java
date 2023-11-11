/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Transaction;

/**
 *
 * @author ADMIN
 */
public class TransactionDAO extends BaseDAO<Transaction> {

    public long getAccountBalanceByUserId(int userId) {
        try {
            String sql = "select *from UserAccountBalance where userId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getLong("balance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean insertAcountBalance(int userId, long price) {
        try {
            String sql = "insert into UserAccountBalance(userId,balance) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setLong(2, price);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAcountBalance(int userId, long price) {
        try {
            String sql1 = "select *from UserAccountBalance where userId=?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, userId);
            ResultSet rs = statement1.executeQuery();
            long balance = 0;
            while (rs.next()) {
                balance = rs.getLong("balance");
            }
            balance += price;
            String sql2 = "update UserAccountBalance set balance=? where userId=?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setLong(1, balance);
            statement2.setInt(2, userId);
            statement2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertTransaction(Transaction tran) {
        try {
            String sql = "insert into TransactionHistory(transactionId,userId,amount,createdDate,content) values (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tran.getTransactionId());
            statement.setInt(2, tran.getUserId());
            statement.setLong(3, tran.getAmount());
            statement.setDate(4, tran.getCreatedDate());
            statement.setString(5, tran.getContent());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Transaction getTransactionById(String transactionId) {
        try {
            String sql = "select *from TransactionHistory where transactionId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, transactionId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Transaction tran = new Transaction();
                tran.setTransactionId(rs.getString(1));
                tran.setUserId(rs.getInt(2));
                tran.setAmount(rs.getLong(3));
                tran.setCreatedDate(rs.getDate(4));
                tran.setContent(rs.getString(5));
                return tran;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
