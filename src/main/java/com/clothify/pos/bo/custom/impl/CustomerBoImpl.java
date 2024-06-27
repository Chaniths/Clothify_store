package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.CustomerDao;
import com.clothify.pos.dto.Customer;
import com.clothify.pos.entity.CustomerEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerBoImpl implements CustomerBo {

   private  final CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
  ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean persist(Customer customer) {
        CustomerEntity entity = mapper.convertValue(customer, CustomerEntity.class);
        return  customerDao.persist(entity);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(mapper.convertValue(customer, CustomerEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return customerDao.delete(id);
    }

    @Override
    public Customer search(String contact) {
        CustomerEntity entity = customerDao.search(contact);
        return mapper.convertValue(entity, Customer.class);
    }

    @Override
    public ObservableList<Customer> searchAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ObservableList<CustomerEntity> customerEntities = customerDao.searchAll();
        customerEntities.forEach(entity -> customers.add(mapper.convertValue(entity,Customer.class)));
        return customers;
    }

    @Override
    public String getLatestId() {
        return customerDao.getLatestId();
    }

    @Override
    public long count() {
        return customerDao.count();
    }
}
