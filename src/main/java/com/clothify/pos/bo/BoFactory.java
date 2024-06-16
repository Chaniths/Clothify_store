package com.clothify.pos.bo;

import com.clothify.pos.bo.custom.impl.LoginBoImpl;
import com.clothify.pos.util.BoType;

public class BoFactory {

    private BoFactory(){}

    private static BoFactory instance;

    public static BoFactory getInstance(){
        return instance != null ? instance :(instance = new BoFactory());
    }

    public <T extends SuperBo>T getBo (BoType type){

        switch (type){
            case LOGIN:return (T) new LoginBoImpl();
        }
        return null;
    }

}
