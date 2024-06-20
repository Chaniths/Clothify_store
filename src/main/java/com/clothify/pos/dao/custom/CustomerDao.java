package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.CustomerEntity;
import javafx.collections.ObservableList;

public interface CustomerDao extends SuperDao {

    boolean save(CustomerEntity entity);

    CustomerEntity search(String searchContact);

    ObservableList<CustomerEntity> searchAll();
}
