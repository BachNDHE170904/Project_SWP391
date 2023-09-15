/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class SendEmail {
    public String getRandom(){
        Random rnd=new Random();
        int number=rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    public boolean sendEmail(UserDetails user,String text){
        boolean test=false;
        
        String toEmail=user.getEmail();
        String fromEmail="happyprogramming551@gmail.com";
        String password="lznv wgsd rbhg kbjt";
        
        try{
            Properties pr=new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            
            
            Session session=Session.getInstance(pr,new Authenticator(){
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication(fromEmail,password);
               } 
            });
            
            Message mess=new MimeMessage(session);
            
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            
            mess.setSubject("User Email Verification");
            
            mess.setText(text);
            
            Transport.send(mess);
            
            test=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return test;
    }
}
