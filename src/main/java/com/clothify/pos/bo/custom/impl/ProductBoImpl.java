package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.ProductBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.ProductDao;
import com.clothify.pos.dto.Product;
import com.clothify.pos.entity.ProductEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductBoImpl implements ProductBo {

    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean persist(Product product) {
        return productDao.persist(mapper.convertValue(product, ProductEntity.class));
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(mapper.convertValue(product, ProductEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return productDao.delete(id);
    }

    @Override
    public Product search(String id) {
        return mapper.convertValue(productDao.search(id), Product.class);
    }

    @Override
    public ObservableList<Product> searchAll() {
        ObservableList<ProductEntity> productEntities = productDao.searchAll();
        ObservableList<Product> products = FXCollections.observableArrayList();
        productEntities.forEach(entity -> products.add(mapper.convertValue(entity, Product.class)));
        return products;
    }

    @Override
    public ObservableList<String> getAllProductIds() {
        return productDao.getAllProductIds();
    }

    @Override
    public String getLatestId() {
        return productDao.getLatestId();
    }

    @Override
    public long count() {
        return productDao.count();
    }
}
