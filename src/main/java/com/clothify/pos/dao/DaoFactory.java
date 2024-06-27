package com.clothify.pos.dao;

import com.clothify.pos.dao.custom.impl.*;
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

    @SuppressWarnings("unchecked")
    public <T extends SuperDao>T getDao (DaoType type){
       switch (type){
           case LOGIN:return (T) new LoginDaoImpl();
           case ORDER:return (T) new OrderDaoImpl();
           case CUSTOMER:return (T) new CustomerDaoImpl();
           case EMPLOYEE:return (T) new EmployeeDaoImpl();
           case INVENTORY:return (T) new InventoryDaoImpl();
           case PRODUCT:return (T) new ProductDaoImpl();
           case SUPPLIER:return (T) new SupplierDaoImpl();
           case OrderDetail:return (T) new OrderDetailsDaoImpl();
           default:
               throw new IllegalArgumentException("Unsupported DaoType: " + type);
       }
    }
}
