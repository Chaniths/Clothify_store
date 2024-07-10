package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Order;
import jakarta.mail.MessagingException;
import javafx.collections.ObservableList;

import java.io.File;

public interface OrderBo extends SuperBo {

    boolean persist(Order order);

    boolean update(Order order);

    boolean delete(String orderId);

    Order search(String id);

    ObservableList<Order> searchAll();

    String getLatestId();

    long count();

    void sendEmail(String receiveEmail, String text, File file) throws MessagingException;


}
