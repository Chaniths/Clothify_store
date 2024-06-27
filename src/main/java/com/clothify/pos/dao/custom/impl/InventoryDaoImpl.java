package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.InventoryDao;
import com.clothify.pos.entity.InventoryEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    @Override
    public boolean persist(InventoryEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(InventoryEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Inventory SET productName=:productName,category=:category,supplierId=:supplierId,qtyOnHand=:qtyOnHand,receivedQty=:receivedQty,unitPrice=:unitPrice,totalInventoryPrice=:totalInventoryPrice WHERE productId=:productId");
        query.setParameter("productName",entity.getProductName());
        query.setParameter("category",entity.getCategory());
        query.setParameter("supplierId",entity.getSupplierId());
        query.setParameter("qtyOnHand",entity.getQtyOnHand());
        query.setParameter("receivedQty",entity.getReceivedQty());
        query.setParameter("unitPrice",entity.getUnitPrice());
        query.setParameter("totalInventoryPrice",entity.getTotalInventoryPrice());
        query.setParameter("productId",entity.getProductId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

   @Override
    public boolean updateStock(String productId, Integer qty) {
       Session session = null;
       Transaction transaction = null;
       try {
           session = HibernateUtil.getSession();
           transaction = session.getTransaction();
           transaction.begin();
           Query query = session.createQuery("UPDATE Inventory SET qtyOnHand = qtyOnHand - :qty WHERE productId = :productId");
           query.setParameter("qty", qty);
           query.setParameter("productId", productId);
           int i = query.executeUpdate();
           transaction.commit();
           return i > 0;
       } catch (Exception e) {
           if (transaction != null) {
               transaction.rollback();
           }
           return false;
       } finally {
           if (session != null) {
               session.close();
           }
       }
   }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Inventory WHERE productId=:id");
        query.setParameter("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public InventoryEntity search(String productId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Inventory WHERE ProductId=:id");
        query.setParameter("id",productId);
        InventoryEntity inventoryEntity = (InventoryEntity) query.uniqueResult();
        session.close();
        return inventoryEntity;
    }

    @Override
    public ObservableList<InventoryEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<InventoryEntity> entityList = session.createQuery("FROM Inventory").list();
        ObservableList<InventoryEntity> inventoryEntities = FXCollections.observableArrayList();
        inventoryEntities.addAll(entityList);
        session.close();
        return inventoryEntities;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT productId FROM Inventory ORDER BY productId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public long count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long singleResult = (long) session.createQuery("SELECT COUNT(*) FROM Inventory").getSingleResult();
        session.close();
        return singleResult;
    }
}
