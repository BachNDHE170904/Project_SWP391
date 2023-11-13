/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

import dal.MentorDAO;
import dal.RequestDAO;
import dal.TransactionDAO;
import dal.UserDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import model.Mentor;
import model.Request;
import model.Status;

public class AddMoneyTask extends TimerTask {

    @Override
    public void run() {
        Date currentDate = new Date();
        RequestDAO dbRequest = new RequestDAO();
        UserDAO dbUser = new UserDAO();
        TransactionDAO dbTransaction = new TransactionDAO();
        List<Request> requests = dbRequest.getRequests();
        for (Request r : requests) {
            Date deadline = r.getDeadline();
            if (r.getStatus().getId() == 2 && deadline.before(currentDate)) {
                dbRequest.updateRequestStatusToClosed(r.getId());
                r.setStatus(new Status(4, "Closed"));
                MentorDAO mentorDb = new MentorDAO();
                Mentor m = mentorDb.getMentorByMentorID(r.getMentorId());
                if (!dbTransaction.insertAcountBalance(m.getUserid(), r.getMenteePrice())) {
                    dbTransaction.updateAcountBalance(m.getUserid(), r.getMenteePrice());
                }
            }
        }
    }

}
