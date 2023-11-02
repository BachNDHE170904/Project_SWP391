/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;
/**
 *
 * @author admin
 */
public class CommentDAO extends BaseDAO<Comment> {
    public void insertCommentAndRateStar(int id, String comment, int rate) {
        try {
            String insertComment = "INSERT INTO Comment(commentDetail, createdDate) VALUES(?,GETDATE())";
            PreparedStatement stm = connection.prepareStatement(insertComment);
            stm.setString(1, comment);
            stm.executeUpdate();
            int commentId = getCommentId();
            String insertRating = "INSERT INTO Rating (rating, commentId, requestId) VALUES (?, ?, ?)";
            PreparedStatement xstm = connection.prepareStatement(insertRating);
            xstm.setInt(1, rate);
            xstm.setInt(2, commentId);
            xstm.setInt(3, id);
            xstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getCommentId() {
        try {
            String sql = "SELECT TOP 1 commentId from Comment order by commentId desc";
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
