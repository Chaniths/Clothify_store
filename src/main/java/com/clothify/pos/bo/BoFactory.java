package com.clothify.pos.bo;

import com.clothify.pos.bo.custom.impl.*;
import com.clothify.pos.util.BoType;

import java.util.Objects;

public class BoFactory {

    private BoFactory(){}

    private static BoFactory instance;

    public static BoFactory getInstance(){
        return instance != null ? instance :(instance = new BoFactory());
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperBo>T getBo (BoType type){

        switch (type){
            case LOGIN: return (T) new LoginBoImpl();
            case ORDER: return (T) new OrderBoImpl();
            case PRODUCT: return (T) new ProductBoImpl();
            case INVENTORY: return (T) new InventoryBoImpl();
            case CUSTOMER: return  (T) new CustomerBoImpl();
            case EMPLOYEE: return  (T) new EmployeeBoImpl();
            case SUPPLIER: return (T) new SupplierBoImpl();
            default:
                throw new IllegalArgumentException("Unsupported BoType: " + type);
        }
    }

}
