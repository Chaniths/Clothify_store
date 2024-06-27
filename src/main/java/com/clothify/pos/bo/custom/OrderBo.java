package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Order;
import javafx.collections.ObservableList;

public interface OrderBo extends SuperBo {

    boolean persist(Order order);

    boolean update(Order order);

    boolean delete(String orderId);

    Order search(String id);

    ObservableList<Order> searchAll();

    String getLatestId();

    long count();


}
