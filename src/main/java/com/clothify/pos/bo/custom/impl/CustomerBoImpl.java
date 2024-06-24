package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.CustomerDao;
import com.clothify.pos.dto.Customer;
import com.clothify.pos.entity.CustomerEntity;
import com.clothify.pos.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class CustomerBoImpl implements CustomerBo {

   private  final CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
   ModelMapper mapper = new ModelMapper();

    @Override
    public boolean persist(Customer customer) {
        return customerDao.persist(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return customerDao.delete(id);
    }

    @Override
    public Customer search(String contact) {
        CustomerEntity entity = customerDao.search(contact);
        return mapper.map(entity, Customer.class);
    }

    @Override
    public ObservableList<Customer> searchAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ObservableList<CustomerEntity> customerEntities = customerDao.searchAll();
        customerEntities.forEach(entity -> customers.add(mapper.map(entity,Customer.class)));
        return customers;
    }

    @Override
    public String getLatestId() {
        return customerDao.getLatestId();
    }

    @Override
    public int count() {
        return customerDao.count();
    }
}
