package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.SupplierDao;
import com.clothify.pos.entity.SupplierEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
  @Override
    public boolean persist(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Supplier SET supplierName=:supplierName,productName=:productName,contact=:contact,email=:email,company=:company WHERE supplierId=:supplierId");
        query.setParameter("supplierName",entity.getSupplierName());
        query.setParameter("productName",entity.getProductName());
        query.setParameter("contact",entity.getContact());
        query.setParameter("email",entity.getEmail());
        query.setParameter("company",entity.getCompany());
        query.setParameter("supplierId",entity.getSupplierId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Supplier WHERE supplierId=:supplierId");
        query.setParameter("supplierId",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public SupplierEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Supplier WHERE supplierId=:supplierId ");
        query.setParameter("supplierId",id);
        SupplierEntity supplierEntity = (SupplierEntity) query.uniqueResult();
        session.close();
        return supplierEntity;
    }

    @Override
    public ObservableList<SupplierEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<SupplierEntity> entityList = session.createQuery("FROM Supplier").list();
        ObservableList<SupplierEntity> supplierEntities = FXCollections.observableArrayList();
        supplierEntities.addAll(entityList);
        session.close();
        return supplierEntities;
    }

    @Override
    public ObservableList<String> getAllIds() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<String> list = session.createQuery("SELECT supplierId FROM Supplier").list();
        ObservableList<String> arrayList = FXCollections.observableArrayList();
        arrayList.addAll(list);
        session.close();
        return arrayList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT supplierId FROM Supplier ORDER BY supplierId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public long count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long singleResult = (long) session.createQuery("SELECT COUNT(*) FROM Supplier").getSingleResult();
        session.close();
        return singleResult;
    }
}
