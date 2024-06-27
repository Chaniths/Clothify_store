package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.OrderDetailsDao;
import com.clothify.pos.entity.OrderDetailEntity;
import com.clothify.pos.util.HibernateUtil;
import org.hibernate.Session;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean persist(OrderDetailEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
