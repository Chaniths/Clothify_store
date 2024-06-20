package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.LoginBo;
import com.clothify.pos.controller.system_pages.OrderPageFormController;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.LoginDao;
import com.clothify.pos.util.DaoType;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.scene.control.Alert;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginBoImpl implements LoginBo {

   private final LoginDao loginDao = DaoFactory.getInstance().getDao(DaoType.LOGIN);

    Random random = new Random();
    String otp = random.nextInt(10000)+"";

    @Override
    public Boolean verifyOtp(String userOtp){
        return userOtp.equals(otp);
    }

    @Override
    public Boolean getEmailInfo(String email){

        String emailText = "Your otp for the login is "+otp+". Enter it in the system to verification.";
        try {
            sendEmail(email,emailText);
            return true;
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR,"Email not relatable with the system");
        }
        return false;

    }

    @Override
    public Message prepareMessage(Session session, String myEmail, String receiveEmail, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                    new InternetAddress(receiveEmail)
            });
            message.setSubject("OTP CODE");
            message.setText(text);

            return message;
        }catch (Exception e){
            Logger.getLogger(OrderPageFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    @Override
    public void sendEmail(String receiveEmail,String text) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myEmail = "chanithwijekoon@gmail.com";
        String password = "cmzdkemiflfpyois";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,password);
            }
        });

        Message message = prepareMessage(session,myEmail,receiveEmail,text);
        Transport.send(message);
    }
}
