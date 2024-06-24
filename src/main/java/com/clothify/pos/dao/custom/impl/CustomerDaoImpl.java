package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.CustomerDao;
import com.clothify.pos.entity.CustomerEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean persist(CustomerEntity entity){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Customer SET name=:name,contact=:contact,email=:email,dob=:dob,address=:address WHERE customerId=:customerId");
        query.setParameter("name",entity.getCustomerName());
        query.setParameter("contact",entity.getContact());
        query.setParameter("email",entity.getEmail());
        query.setParameter("dob",entity.getDob());
        query.setParameter("address",entity.getAddress());
        query.setParameter("customerId",entity.getCustomerId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Customer WHERE customerId=:customerId");
        query.setParameter("customerId",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public CustomerEntity search(String searchContact) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Customer WHERE contact=:contact ");
        query.setParameter("contact",searchContact);
        CustomerEntity customerEntity = (CustomerEntity) query.uniqueResult();
        session.close();
        return customerEntity;
    }

    @Override
    public ObservableList<CustomerEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<CustomerEntity> entityList = session.createQuery("FROM Customer").list();
        ObservableList<CustomerEntity> customerList = FXCollections.observableArrayList();
        customerList.addAll(entityList);
        session.close();
        return customerList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public int count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        int singleResult = (int) session.createQuery("SELECT COUNT(*) FROM Customer").getSingleResult();
        session.close();
        return singleResult;
    }

}
