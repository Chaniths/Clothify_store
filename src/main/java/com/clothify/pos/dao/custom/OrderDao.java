package com.clothify.pos.dao.custom;


import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.OrderEntity;
import javafx.collections.ObservableList;

public interface OrderDao extends SuperDao {

    boolean persist(OrderEntity entity);

    boolean update(OrderEntity entity);

    boolean delete(String orderId);

    OrderEntity search(String id);

    ObservableList<OrderEntity> searchAll();

    String getLatestId();

    long count();
}
