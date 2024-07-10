package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.OrderBo;
import com.clothify.pos.controller.system_pages.OrderPageFormController;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.OrderDao;
import com.clothify.pos.dto.Order;
import com.clothify.pos.entity.OrderEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderBoImpl implements OrderBo {

    private final OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean persist(Order order) {
        return orderDao.persist(
                mapper.convertValue(order, OrderEntity.class)
        );
    }

    @Override
    public boolean update(Order order) {

        return orderDao.update(
                mapper.convertValue(order, OrderEntity.class)
        );
    }

    @Override
    public boolean delete(String orderId) {
        return orderDao.delete(orderId);
    }

    @Override
    public Order search(String id) {
        return mapper.convertValue(
                orderDao.search(id),Order.class
        );
    }

    @Override
    public ObservableList<Order> searchAll() {
        ObservableList<OrderEntity> orderEntities = orderDao.searchAll();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orderEntities.forEach(entity ->
                orders.add(mapper.convertValue(entity,Order.class))
        );
        return orders;
    }

    @Override
    public String getLatestId() {
        return orderDao.getLatestId();
    }

    @Override
    public long count() {
        return orderDao.count();
    }

    public Message prepareMessage(Session session, String myEmail, String receiveEmail, String text,File file) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                    new InternetAddress(receiveEmail)
            });
            message.setSubject("Thank You for Your Purchase! Enjoy Your New Look with Clothify");
            message.setText(text);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.attachFile(file);
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);

            return message;
        }catch (Exception e){
            Logger.getLogger(OrderPageFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }


    @Override
    public void sendEmail(String receiveEmail, String text, File file) throws MessagingException {

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

        Message message = prepareMessage(session,myEmail,receiveEmail,text,file);
        Transport.send(message);
    }
}
