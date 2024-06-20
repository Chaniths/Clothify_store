package com.clothify.pos.bo;

import com.clothify.pos.bo.custom.impl.LoginBoImpl;
import com.clothify.pos.bo.custom.impl.OrderBoImpl;
import com.clothify.pos.util.BoType;

import java.util.Objects;

public class BoFactory {

    private BoFactory(){}

    private static BoFactory instance;

    public static BoFactory getInstance(){
        return instance != null ? instance :(instance = new BoFactory());
    }

    public <T extends SuperBo>T getBo (BoType type){

        if (Objects.requireNonNull(type) == BoType.LOGIN) {
            return (T) new LoginBoImpl();
        } else if (type == BoType.ORDER) {
            return (T) new OrderBoImpl();
        }
        return null;
    }

}
