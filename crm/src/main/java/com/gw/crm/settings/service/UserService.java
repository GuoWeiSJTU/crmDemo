package com.gw.crm.settings.service;

import com.gw.crm.exception.LoginException;
import com.gw.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd,String ip) throws LoginException;
}
