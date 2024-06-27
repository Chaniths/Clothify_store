package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Customer;
import javafx.collections.ObservableList;


public interface CustomerBo extends SuperBo {

    boolean persist(Customer customer);

    boolean update(Customer customer);

    boolean delete(String id);

    Customer search(String contact);

   ObservableList<Customer> searchAll();

    String getLatestId();

    long count();

}
