package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.OrderBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.OrderDao;
import com.clothify.pos.dto.Order;
import com.clothify.pos.entity.OrderEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
