package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.OrderDetailEntity;

public interface OrderDetailsDao extends SuperDao {

    boolean persist(OrderDetailEntity entity);
}
