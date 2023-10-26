package dal;

import dal.BaseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Request;

public class RequestDAO extends BaseDAO<Request> {

    public ArrayList<Request> getRequests() {
        ArrayList<Request> skills = new ArrayList<>();
        try {
            String sql = "  select rd.requestId, u.username, rd.title, rd.statusId from RequestDetail rd \n"
                    + "  inner join Requests r on r.requestId = rd.requestId inner join Users u on u.userId = r.userId inner join Statuses s on s.statusId = rd.statusId";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                skills.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skills;
    }

    public ArrayList<Request> searchRequests(String search) {
        ArrayList<Request> skills = new ArrayList<>();
        try {
            String sql = "  select rd.requestId, u.username, rd.title, rd.statusId from RequestDetail rd \n"
                    + "  inner join Requests r on r.requestId = rd.requestId inner join Users u on u.userId = r.userId inner join Statuses s on s.statusId = rd.statusId\n"
                    + "  where rd.title like '%"+search+"%' or u.username like '%"+search+"%'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                skills.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skills;
    }

}
