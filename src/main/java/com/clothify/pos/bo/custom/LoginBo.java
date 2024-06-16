package com.clothify.pos.bo.custom;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;

public interface LoginBo {
    Boolean verifyOtp(String userOtp);

    Boolean getEmailInfo(String email);

    Message prepareMessage(Session session, String myEmail, String receiveEmail, String text);

    void sendEmail(String receiveEmail,String text) throws MessagingException;
}
