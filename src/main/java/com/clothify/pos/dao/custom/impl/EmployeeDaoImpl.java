package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.EmployeeDao;
import com.clothify.pos.entity.EmployeeEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean persist(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Employee SET employeeName=:employeeName,age=:age,nationalId=:nationalId,contact=:contact,address=:address,recruited=:recruited WHERE employeeId=:employeeId");
        query.setParameter("employeeName",entity.getEmployeeName());
        query.setParameter("age",entity.getAge());
        query.setParameter("nationalId",entity.getNationalId());
        query.setParameter("contact",entity.getContact());
        query.setParameter("address",entity.getAddress());
        query.setParameter("recruited",entity.getRecruited());
        query.setParameter("employeeId",entity.getEmployeeId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Employee WHERE employeeId=:employeeId");
        query.setParameter("employeeId",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public EmployeeEntity search(String id){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Employee WHERE employeeId=:employeeId ");
        query.setParameter("employeeId",id);
        EmployeeEntity employeeEntity = (EmployeeEntity) query.uniqueResult();
        session.close();
        return employeeEntity;
    }

    @Override
    public ObservableList<EmployeeEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<EmployeeEntity> entityList = session.createQuery("FROM Employee").list();
        ObservableList<EmployeeEntity> employeeList = FXCollections.observableArrayList();
        employeeList.addAll(entityList);
        session.close();
        return employeeList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public int count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        int singleResult = (int) session.createQuery("SELECT COUNT(*) FROM Employee").getSingleResult();
        session.close();
        return singleResult;
    }
}
