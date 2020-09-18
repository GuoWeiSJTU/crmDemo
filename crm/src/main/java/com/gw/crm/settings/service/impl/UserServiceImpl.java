package com.gw.crm.settings.service.impl;

import com.gw.crm.exception.LoginException;
import com.gw.crm.settings.dao.UserDao;
import com.gw.crm.settings.domain.User;
import com.gw.crm.settings.service.UserService;
import com.gw.crm.utils.DateTimeUtil;
import com.gw.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);
        if (user == null){
            throw new LoginException("账号密码错误");
        }

        String expireTime = user.getExpireTime();
        String currnetTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currnetTime)<0){
            throw new LoginException("账号失效");
        }

        if ("0".equals(user.getLockState())){
            throw new LoginException("账号锁定");
        }
        String allowIps = user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }

        return user;

    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();

        return userList;
    }
}
