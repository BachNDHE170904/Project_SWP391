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
    
    public ArrayList<Transaction> getAllTransaction(int userId) {
        ArrayList<Transaction> trans = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TransactionHistory where userId = ? order by createdDate desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Transaction ts = new Transaction();
                ts.setTransactionId(rs.getString("transactionId"));
                ts.setCreatedDate(rs.getDate("createdDate"));
                ts.setAmount(rs.getLong("amount"));
                ts.setContent(rs.getString("content"));
                trans.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trans;
    }

    public int countTransactionByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM TransactionHistory WHERE userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Transaction> getPagingTransactionByUserID(int userId, int index) {
        try {
            ArrayList<Transaction> trans = new ArrayList<>();
            String sql = "Select * from TransactionHistory t where userId = ? order by t.createdDate desc offset ? rows fetch next 10 rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            int n = (index - 1) * 10;
            stm.setInt(2, n);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getString("transactionId"));
                transaction.setCreatedDate(rs.getDate("createdDate"));
                transaction.setAmount(rs.getLong("amount"));
                transaction.setContent(rs.getString("content"));
                trans.add(transaction);
            }
            return trans;
        } catch(SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
