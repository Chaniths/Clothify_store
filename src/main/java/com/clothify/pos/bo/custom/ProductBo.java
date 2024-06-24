package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Product;
import javafx.collections.ObservableList;

public interface ProductBo extends SuperBo {

    boolean persist(Product product);

    boolean update(Product product);

    boolean delete(String id);

    Product search(String id);

    ObservableList<Product> searchAll();

    ObservableList<String> getAllProductIds();

    String getLatestId();

    int count();
}
