package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.ProductDao;
import com.clothify.pos.entity.ProductEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
   @Override
    public boolean persist(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Product SET productName=:productName,supplierId=:supplierId,unitPrice=:unitPrice,category=:category,qty=:qty WHERE productId=:productId");
        query.setParameter("productName",entity.getProductName());
        query.setParameter("supplierId",entity.getSupplierId());
        query.setParameter("unitPrice",entity.getUnitPrice());
        query.setParameter("category",entity.getCategory());
        query.setParameter("qty",entity.getQty());
        query.setParameter("productId",entity.getProductId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Product WHERE productId=:productId");
        query.setParameter("employeeId",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public ProductEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Product WHERE productId=:productId ");
        query.setParameter("productId",id);
        ProductEntity productEntity = (ProductEntity) query.uniqueResult();
        session.close();
        return productEntity;
    }

    @Override
    public ObservableList<ProductEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ProductEntity> entityList = session.createQuery("FROM Product").list();
        ObservableList<ProductEntity> productEntities = FXCollections.observableArrayList();
        productEntities.addAll(entityList);
        session.close();
        return productEntities;
    }

    @Override
    public ObservableList<String> getAllProductIds() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<String> list = session.createQuery("SELECT productId FROM Product ").list();
        ObservableList<String> productIds = FXCollections.observableArrayList();
        productIds.addAll(list);
        return productIds;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT productId FROM Product ORDER BY productId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public long count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long singleResult = (long) session.createQuery("SELECT COUNT(*) FROM Product").getSingleResult();
        session.close();
        return singleResult;
    }
}
