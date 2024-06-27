package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.OrderDetail;

public interface OrderDetailsBo extends SuperBo {

    boolean persist(OrderDetail orderDetail);
}
