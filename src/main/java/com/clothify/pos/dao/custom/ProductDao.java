package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.ProductEntity;
import javafx.collections.ObservableList;

public interface ProductDao extends SuperDao {

    boolean persist(ProductEntity entity);

    boolean update(ProductEntity entity);

    boolean delete(String id);

    ProductEntity search(String id);

    ObservableList<ProductEntity> searchAll();

    ObservableList<String> getAllProductIds();

    String getLatestId();

    long count();
}
