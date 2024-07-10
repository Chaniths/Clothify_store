package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.OrderDao;
import com.clothify.pos.entity.OrderEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean persist(OrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Orders SET customerId=:customerId,customerName=:customerName,contact=:contact,date=:date,orderDetails=:orderDetails,total=:total,status=:status WHERE orderId=:orderId");
        query.setParameter("customerId",entity.getCustomerId());
        query.setParameter("customerName",entity.getCustomerName());
        query.setParameter("contact",entity.getContact());
        query.setParameter("date",entity.getDate());
        query.setParameter("orderDetails",entity.getOrderDetails());
        query.setParameter("total",entity.getTotal());
        query.setParameter("status",entity.getStatus());
        query.setParameter("orderId",entity.getOrderId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String orderId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Orders WHERE orderId=:orderId");
        query.setParameter("orderId",orderId);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public OrderEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM Orders WHERE orderId=:orderId");
        query.setParameter("orderId",id);
        OrderEntity orderEntity = (OrderEntity) query.uniqueResult();
        session.close();
        return orderEntity;
    }

    @Override
    public ObservableList<OrderEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<OrderEntity> entityList = session.createQuery("FROM Orders").list();
        ObservableList<OrderEntity> orderList = FXCollections.observableArrayList();
        orderList.addAll(entityList);
        session.close();
        return orderList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public long count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long singleResult = (long) session.createQuery("SELECT COUNT(*) FROM Orders").getSingleResult();
        session.close();
        return singleResult;
    }
}
