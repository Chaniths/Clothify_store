package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Customer;


public interface CustomerBo extends SuperBo {

    boolean saveCustomer(Customer customer);

}
