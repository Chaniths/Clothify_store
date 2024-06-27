package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Login;

import java.util.Map;

public interface LoginBo extends SuperBo {

    Boolean persist(Login login);

    Boolean update(Login login,String otp);

    Boolean accountVerify(String email,String password);

    Boolean getEmailInfo(String email);

    Map<String,String> getEmailData(String email);

    String getLatestId();

    long count();

}
