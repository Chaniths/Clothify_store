package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.LoginEntity;
import javafx.collections.ObservableList;

public interface LoginDao extends SuperDao {

    boolean persist(LoginEntity loginEntity);

    LoginEntity getLogin(String email);

    ObservableList<LoginEntity> searchAll();

    boolean update(LoginEntity entity);

    boolean delete(String id);



}
