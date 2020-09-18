package com.gw.crm.settings.service;

import com.gw.crm.exception.LoginException;
import com.gw.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd,String ip) throws LoginException;

    List<User> getUserList();

}
