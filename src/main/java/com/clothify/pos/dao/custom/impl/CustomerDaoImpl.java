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
    public boolean save(CustomerEntity entity){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public CustomerEntity search(String searchContact) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM customer WHERE contact=:contact ");
        query.setParameter("contact",searchContact);
        CustomerEntity customerEntity = (CustomerEntity) query.uniqueResult();
        session.close();
        return customerEntity;
    }

    @Override
    public ObservableList<CustomerEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<CustomerEntity> entityList = session.createQuery("FROM customer").list();
        ObservableList<CustomerEntity> customerList = FXCollections.observableArrayList();
        boolean b = customerList.addAll(entityList);
        if(b) session.close();
        return customerList;
    }


}
