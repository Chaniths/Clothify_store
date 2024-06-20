package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.CustomerDao;
import com.clothify.pos.dto.Customer;
import com.clothify.pos.entity.CustomerEntity;
import com.clothify.pos.util.DaoType;
import org.modelmapper.ModelMapper;

public class CustomerBoImpl implements CustomerBo {

   private  final CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(Customer customer) {
        return customerDao.save(new ModelMapper().map(customer, CustomerEntity.class));
    }
}
