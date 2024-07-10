package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.SupplierEntity;
import javafx.collections.ObservableList;

public interface SupplierDao extends SuperDao {

    boolean persist(SupplierEntity entity);

    boolean update(SupplierEntity entity);

    boolean delete(String id);

    SupplierEntity search(String id);

    ObservableList<SupplierEntity> searchAll();

    ObservableList<String> getAllIds();

    String getLatestId();

    long count();
}
