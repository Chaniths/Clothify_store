package com.clothify.pos.dao;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.bo.custom.impl.LoginBoImpl;
import com.clothify.pos.dao.custom.impl.CustomerDaoImpl;
import com.clothify.pos.dao.custom.impl.LoginDaoImpl;
import com.clothify.pos.dao.custom.impl.OrderDaoImpl;
import com.clothify.pos.util.BoType;
import com.clothify.pos.util.DaoType;

public class DaoFactory {

    private DaoFactory(){}

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if(instance == null){
            return instance = new DaoFactory();
        }
        return instance;
    }

    public <T extends SuperDao>T getDao (DaoType type){
       switch (type){
           case LOGIN:return (T) new LoginDaoImpl();
           case ORDER:return (T) new OrderDaoImpl();
           case CUSTOMER:return (T) new CustomerDaoImpl();
       }
       return null;
    }
}
