package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.OrderDetailsBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.OrderDetailsDao;
import com.clothify.pos.dto.OrderDetail;
import com.clothify.pos.entity.OrderDetailEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDetailsBoImpl implements OrderDetailsBo {

    private final OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.OrderDetail);
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public boolean persist(OrderDetail orderDetail) {
        return orderDetailsDao.persist(mapper.convertValue(orderDetail, OrderDetailEntity.class));
    }
}
