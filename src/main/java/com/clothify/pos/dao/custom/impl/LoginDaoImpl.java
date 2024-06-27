package com.clothify.pos.dao.custom.impl;

import com.clothify.pos.dao.custom.LoginDao;
import com.clothify.pos.entity.LoginEntity;
import com.clothify.pos.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
    //add
    @Override
    public boolean persist(LoginEntity loginEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(loginEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    //get specific user to verify
    @Override
    public LoginEntity getLogin(String email) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Login WHERE email=:email");
        query.setParameter("email", email);
        LoginEntity userEntity = (LoginEntity) query.uniqueResult();
        session.close();
        return userEntity;

    }


    //get All users
    @Override
    public ObservableList<LoginEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        List<LoginEntity> userList = session.createQuery("FROM Login").list();
        ObservableList<LoginEntity> loginEntities = FXCollections.observableArrayList();
        for (LoginEntity login : userList) {
            loginEntities.add(login);
        }
        session.close();
        return loginEntities;
    }

    //update User

    @Override
    public boolean update(LoginEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery(
                "UPDATE Login SET adminType=:adminType,email=:email,password=:password WHERE loginId=:loginId");
        query.setParameter("adminType",entity.getAdminType());
        query.setParameter("email",entity.getEmail());
        query.setParameter("password",entity.getPassword());
        query.setParameter("loginId",entity.getLoginId());
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    //update the password
    public boolean update(String email,String password){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE Login SET password =:p WHERE email =:e");
        query.setParameter("p",password);
        query.setParameter("e",email);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    //delete user
    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Login WHERE loginId=:loginId");
        query.setParameter("loginId",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    //Last Id
    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT loginId FROM Login ORDER BY loginId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public long count(){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        long singleResult = (long) session.createQuery("SELECT COUNT(*) FROM Login").getSingleResult();
        session.close();
        return singleResult;
    }
}
