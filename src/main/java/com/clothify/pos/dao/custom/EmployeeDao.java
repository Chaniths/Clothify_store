package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.EmployeeEntity;
import javafx.collections.ObservableList;

public interface EmployeeDao extends SuperDao {

    boolean persist(EmployeeEntity entity);

    boolean update(EmployeeEntity entity);

    boolean delete(String id);

    EmployeeEntity search(String id);

    ObservableList<EmployeeEntity> searchAll();

    String getLatestId();

    long count();

}
